package com.excilys.computerdatabase.persistence.mapping.request;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
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
    ComputerService cpuServ = ComputerService.getInstance();

    // Get the attribute and parameters from the page
    String numPageReq = request.getParameter(PARAM_NUMPAGE);
    String limitReq = request.getParameter(PARAM_LIMIT);

    // New Pagination with the empty list and initial parameters
    int countEntries = cpuServ.countEntries();

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
    // Mapping from cpu to cpuDto for all results from service
    for (Computer cpu : cpuServ.listComputers(offset, pageSize)) {
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
    CompanyService cpnServ = CompanyService.getInstance();

    // Uploading the companies' list
    int countEntries = cpnServ.countEntries();

    Pagination page = Pagination.builder().cpnList(cpnServ.listCompanies(0, countEntries)).build();

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
    CompanyService cpnServ = CompanyService.getInstance();
    // Uploading the companies' list
    int countEntries = cpnServ.countEntries();

    Pagination page = Pagination.builder().cpnList(cpnServ.listCompanies(0, countEntries)).build();

    return page;
  }
  
  

}
