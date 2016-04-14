package com.excilys.computerdatabase.persistence.mapping.request;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.persistence.mapping.query.Query;
import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;
import com.excilys.computerdatabase.validation.PaginationValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class PageRequestMapper {

  // Params for Dashboard
  private static final String PARAM_NUMPAGE = "numPage";
  private static final String PARAM_LIMIT = "limit";
  private static final int INIT_NUMPAGE = 1;
  private static final int INIT_LIMIT = 10;
  private static final String PARAM_DELETE = "selection";
  private static final String PARAM_SEARCH = "search";
  private static final String PARAM_ORDERNAME = "ordName";
  private static final String PARAM_ORDERINTRODUCED = "ordIntr";
  private static final String PARAM_ORDERDISCONTINUED = "ordDisc";
  private static final String PARAM_ORDERCOMPANY = "ordCpn";

  private static Logger log = Logger.getLogger(PageRequestMapper.class);

  /**
   * Mapper for the request from dashboard. If the parameters are null,
   * initialize the map. Else, try to validate values, and return a new page
   * with it.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination fromDashboard(HttpServletRequest request) {

    // Get the service instance
    ComputerServiceImpl cpuServ = ComputerServiceImpl.getInstance();

    // Get the attribute and parameters from the page
    String numPageReq = request.getParameter(PARAM_NUMPAGE);
    String limitReq = request.getParameter(PARAM_LIMIT);

    // New Pagination with the empty list and initial parameters
    int countEntries = cpuServ.count(Query.builder().build());

    Pagination page = Pagination.builder().cpuPageNumber(INIT_NUMPAGE).cpuPageSize(INIT_LIMIT)
        .cpuTotalEntries(countEntries)
        .cpuNbPages(
            (int) Math.floor(countEntries / INIT_LIMIT) + (countEntries % INIT_LIMIT == 0 ? 0 : 1))
        .cpuList(new ArrayList<>()).build();

    // Page number checking
    String numPageRes = PaginationValidator.getInstance().numPageIsValid(numPageReq,
        page.getCpuNbPages());

    // Limit of entries checking
    String limitRes = PaginationValidator.getInstance().pageSizeIsValid(limitReq);

    // Actualize numPage and limit, don't forget to actualize NbPages as soon as
    // limit has changed
    if (numPageRes == null) {
      // numPage is ok
      page.setCpuPageNumber(Integer.parseInt(numPageReq));
      if (limitRes == null) {
        // both are ok
        int limit = Integer.parseInt(limitReq);
        page.setCpuPageSize(limit);
        // actualize nbPages
        page.setCpuNbPages((int) Math.floor(page.getCpuTotalEntries() / limit)
            + (page.getCpuTotalEntries() % limit == 0 ? 0 : 1));
      } else {
        // limit is wrong
        page.setCpuPageSize(INIT_LIMIT);
        // actualize nbPages
        page.setCpuNbPages((int) Math.floor(page.getCpuTotalEntries() / INIT_LIMIT)
            + (page.getCpuTotalEntries() % INIT_LIMIT == 0 ? 0 : 1));
        log.info(limitRes);
      }
    } else {
      // numPage is wrong
      page.setCpuPageNumber(INIT_NUMPAGE);
      if (limitRes == null) {
        // limit is ok
        int limit = Integer.parseInt(limitReq);
        page.setCpuPageSize(limit);
        // actualize nbPages
        page.setCpuNbPages((int) Math.floor(page.getCpuTotalEntries() / limit)
            + (page.getCpuTotalEntries() % limit == 0 ? 0 : 1));
        log.info("Limit has changed, page number has been reset : 1.");
      } else {
        // both are wrong
        page.setCpuPageSize(INIT_LIMIT);
        // actualize nbPages
        page.setCpuNbPages((int) Math.floor(page.getCpuTotalEntries() / INIT_LIMIT)
            + (page.getCpuTotalEntries() % INIT_LIMIT == 0 ? 0 : 1));
        log.info(numPageRes);
        log.info(limitRes);
      }
    }

    // We now refresh the list
    // Need the numPage and the size
    int numPage = page.getCpuPageNumber();
    int pageSize = page.getCpuPageSize();
    // Offset
    int offset = (numPage - 1) * pageSize;
    // Parameters of research and ordering
    String filter = request.getParameter(PARAM_SEARCH);
    request.setAttribute("search", request.getParameter(PARAM_SEARCH));
    String orderName = request.getParameter(PARAM_ORDERNAME);
    String orderIntroduced = request.getParameter(PARAM_ORDERINTRODUCED);
    String orderDiscontinued = request.getParameter(PARAM_ORDERDISCONTINUED);
    String orderCompany = request.getParameter(PARAM_ORDERCOMPANY);
    // Mapping from cpu to cpuDto for all results from service
    Query query = Query.builder().filter(filter)
        // .orderName(orderName)
        // .orderIntroduced(orderIntroduced)
        // .orderDiscontinued(orderDiscontinued)
        // .orderCompany(orderCompany)
        .offset(offset).limit(pageSize).build();
    for (Computer cpu : cpuServ.list(query)) {
      page.getCpuList().add(ComputerDaoToDto.getInstance().map(cpu));
    }
    return page;
  }

  /**
   * Mapper for the request from addComputer.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination fromAdd(HttpServletRequest request) {
    // Get the service instance
    CompanyServiceImpl cpnServ = CompanyServiceImpl.getInstance();

    // Uploading the companies' list
    Query query = Query.builder().offset(0).build();
    Pagination page = Pagination.builder().cpnList(cpnServ.list(query)).build();

    return page;
  }

  /**
   * Mapper for the request from editComputer.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination fromEdit(HttpServletRequest request) {
    // Get the service instance
    CompanyServiceImpl cpnServ = CompanyServiceImpl.getInstance();
    // Uploading the companies' list
    Query query = Query.builder().offset(0).build();
    Pagination page = Pagination.builder().cpnList(cpnServ.list(query)).build();

    return page;
  }

  /**
   * Mapper for the request from dashboard when deleting.
   * 
   * @param request
   *          from the jsp
   */
  public static void delete(HttpServletRequest request) {
    // Get the service instance
    ComputerServiceImpl cpuServ = ComputerServiceImpl.getInstance();
    // Getting the list of computers to delete
    String[] deleteIds = request.getParameter(PARAM_DELETE).split(",");
    for (String idReq : deleteIds) {
      cpuServ.delete(Long.parseLong(idReq));
    }
  }

}
