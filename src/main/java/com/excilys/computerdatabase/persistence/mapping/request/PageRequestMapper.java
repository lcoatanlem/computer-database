package com.excilys.computerdatabase.persistence.mapping.request;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validation.PaginationValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class PageRequestMapper {

  private static final String ATTR_PAGE = "page";
  private static final String ATTR_NUMPAGE = "numPage";
  private static final String ATTR_LIMIT = "limit";
  private static final int INIT_NUMPAGE = 1;
  private static final int INIT_LIMIT = 10;

  private static Logger log = Logger.getLogger(PageRequestMapper.class);

  /**
   * Mapper for the request. If the request attribute page is null, initialize it. Else, try to
   * validate attribute's values, and return a new page with it.
   * 
   * @param request
   *          from the jsp
   * @return a new page to transmit to the jsp
   */
  public static Pagination map(HttpServletRequest request) {

    // We get the service instance
    ComputerService cpuServ = ComputerService.getInstance();

    // We get the attribute and parameters from the page
    Pagination page = (Pagination) request.getAttribute(ATTR_PAGE);
    String numPageReq = request.getParameter(ATTR_NUMPAGE);
    String limitReq = request.getParameter(ATTR_LIMIT);

    // Pagination initialization if page is null
    if (page == null) {
      // New Pagination with the empty list and initial parameters
      int countEntries = cpuServ.countEntries();
      page = Pagination.builder().cpuPageNumber(INIT_NUMPAGE).cpuPageSize(INIT_LIMIT)
          .cpuTotalEntries(countEntries).cpuNbPages((int) Math.ceil(countEntries / INIT_LIMIT))
          .cpuList(new ArrayList<>()).build();
    } else {
      
      // Page number checking
      String numPageRes = PaginationValidator.getInstance().numPageIsValid(numPageReq,
          page.getCpuNbPages());
      
      // Limit of entries checking
      String limitRes = PaginationValidator.getInstance().pageSizeIsValid(limitReq);
      
      if (numPageRes == null) {
        // numPage is ok
        page.setCpnPageNumber(Integer.parseInt(numPageReq));
        if (limitRes == null) {
          // both are ok
          page.setCpuPageSize(Integer.parseInt(limitReq));
        } else {
          // limitRes is wrong
          page.setCpuPageSize(INIT_LIMIT);
          log.info(limitRes);
        }
      } else {
        // numPage is wrong
        if (limitRes == null) {
          page.setCpuPageSize(Integer.parseInt(limitReq));
          page.setCpnPageNumber(INIT_NUMPAGE);
          log.info(numPageRes);
        } else {
          // both are wrong
          page.setCpuPageSize(INIT_LIMIT);
          page.setCpnPageNumber(INIT_NUMPAGE);
          log.info(numPageRes);
          log.info(limitRes);
        }
      }

      // We now refresh the list
      // Need the numPage and the size
      int numPage = page.getCpuPageNumber();
      int pageSize = page.getCpuPageSize();
      // Cleaning the previous list in Pagination
      page.getCpuList().clear();
      // Offset
      int offset = (numPage - 1) * pageSize;
      // Mapping from cpu to cpuDto for all results from service
      for (Computer cpu : cpuServ.listComputers(offset, pageSize)) {
        page.getCpuList().add(ComputerDaoToDto.getInstance().map(cpu));
      }
    }
    return page;
  }

}
