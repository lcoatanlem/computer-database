package com.excilys.computerdatabase.mapping.request;

import com.excilys.computerdatabase.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.mapping.query.Query;
import com.excilys.computerdatabase.mapping.query.Query.Order;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.pagination.Pagination.Builder;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.validation.PaginationValidator;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PageRequestMapper {

  // Params for Dashboard
  private static final String PARAM_NUMPAGE = "numPage";
  private static final String PARAM_LIMIT = "limit";
  private static final int INIT_NUMPAGE = 1;
  private static final int INIT_LIMIT = 10;
  private static final String PARAM_DELETE = "selection";
  private static final String PARAM_SEARCH = "search";
  private static final String PARAM_ORDERNAME = "nameOrder";
  private static final String PARAM_ORDERINTRODUCED = "introducedOrder";
  private static final String PARAM_ORDERDISCONTINUED = "discontinuedOrder";
  private static final String PARAM_ORDERCOMPANY = "companyOrder";

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
  public static Pagination fromDashboard(HttpServletRequest request,
      IService<Computer> computerService) {

    // Research
    String filter = request.getParameter(PARAM_SEARCH);
    // Query for the count of entries
    Query queryCount = Query.builder().filter(filter).build();
    // Getting the result of the request
    int countEntries = computerService.count(queryCount);

    // New page, we need the count of entries
    Builder pageBuilder = Pagination.builder().cpuTotalEntries(countEntries);
    if (filter != null) {
      pageBuilder = pageBuilder.search(filter);
    }

    // Page number and limit
    String numPageReq = request.getParameter(PARAM_NUMPAGE);
    String limitReq = request.getParameter(PARAM_LIMIT);
    // Limit of entries checking && computing of the number of pages
    String limitRes = PaginationValidator.getInstance().pageSizeIsValid(limitReq);
    int limit;
    if (limitRes == null) {
      // limit is ok
      limit = Integer.parseInt(limitReq);
    } else {
      // limit is wrong, log the error and put initial value
      log.info(limitRes);
      limit = INIT_LIMIT;
    }
    // Define limit and number of pages
    int nbPages = (int) Math.floor(countEntries / limit) + (countEntries % limit == 0 ? 0 : 1);
    pageBuilder = pageBuilder.cpuPageSize(limit).cpuNbPages(nbPages);
    // Page number checking
    int numPage;
    String numPageRes = PaginationValidator.getInstance().numPageIsValid(numPageReq, nbPages);
    if (numPageRes == null) {
      // numPage is ok
      numPage = Integer.parseInt(numPageReq);
    } else {
      // numPage is wrong, log the error and put initial value
      log.info(numPageRes);
      numPage = INIT_NUMPAGE;
    }
    // Define numPage
    pageBuilder = pageBuilder.cpuPageNumber(numPage);

    // We now make a request with all parameters
    Order nameOrder = Order.safeValueOf(request.getParameter(PARAM_ORDERNAME));
    if (nameOrder != null) {
      pageBuilder = pageBuilder.nameOrder(nameOrder.toString());
    }
    Order introducedOrder = Order.safeValueOf(request.getParameter(PARAM_ORDERINTRODUCED));
    if (introducedOrder != null) {
      pageBuilder = pageBuilder.introducedOrder(introducedOrder.toString());
    }
    Order discontinuedOrder = Order.safeValueOf(request.getParameter(PARAM_ORDERDISCONTINUED));
    if (discontinuedOrder != null) {
      pageBuilder = pageBuilder.discontinuedOrder(discontinuedOrder.toString());
    }
    Order companyOrder = Order.safeValueOf(request.getParameter(PARAM_ORDERCOMPANY));
    if (companyOrder != null) {
      pageBuilder = pageBuilder.companyOrder(companyOrder.toString());
    }
    // Compute the offset
    int offset = (numPage - 1) * limit;
    // The Query itself
    Query query = Query.builder().filter(filter).nameOrder(nameOrder)
        .introducedOrder(introducedOrder).discontinuedOrder(discontinuedOrder)
        .companyOrder(companyOrder).offset(offset).limit(limit).build();
    // We create a new List
    List<ComputerDto> list = new ArrayList<>();
    // In which we map all results to dto
    for (Computer cpu : computerService.list(query)) {
      list.add(ComputerDaoToDto.getInstance().map(cpu));
    }
    // Define list of results into the page
    pageBuilder = pageBuilder.cpuList(list);

    return pageBuilder.build();
  }

  /**
   * Mapper for the request from addComputer.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination fromAdd(HttpServletRequest request, IService<Company> companyService) {
    // Uploading the companies' list
    Query query = Query.builder().offset(0).build();
    Pagination page = Pagination.builder().cpnList(companyService.list(query)).build();

    return page;
  }

  /**
   * Mapper for the request from editComputer.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination fromEdit(HttpServletRequest request, IService<Company> companyService) {
    // Uploading the companies' list
    Query query = Query.builder().offset(0).build();
    Pagination page = Pagination.builder().cpnList(companyService.list(query)).build();

    return page;
  }

  /**
   * Mapper for the request from dashboard when deleting.
   * 
   * @param request
   *          from the jsp
   */
  public static void delete(HttpServletRequest request, IService<Computer> computerService) {
    // Getting the list of computers to delete
    String[] deleteIds = request.getParameter(PARAM_DELETE).split(",");
    for (String idReq : deleteIds) {
      computerService.delete(Long.parseLong(idReq));
    }
  }

}
