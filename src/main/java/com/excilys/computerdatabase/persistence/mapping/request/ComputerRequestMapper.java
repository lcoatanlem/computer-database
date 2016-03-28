package com.excilys.computerdatabase.persistence.mapping.request;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ComputerRequestMapper {
  private static final String PARAM_NAME = "computerName";
  private static final String PARAM_INTRODUCED = "introduced";
  private static final String PARAM_DISCONTINUED = "discontinued";
  private static final String PARAM_IDCPN = "idCpn";

  /**
   * Mapping a request (post) to a ComputerDto.
   * 
   * @param list
   * 
   * @return a ComputerDto from the request
   */
  public static ComputerDto toDto(HttpServletRequest request, List<Company> list) {
    // Name
    String nameReq = request.getParameter(PARAM_NAME).trim();
    String name = (nameReq.isEmpty() ? null : nameReq);
    // Introduced
    String introducedReq = request.getParameter(PARAM_INTRODUCED).trim();
    String introduced = (introducedReq.isEmpty() ? null : introducedReq);
    // Discontinued
    String discontinuedReq = request.getParameter(PARAM_DISCONTINUED).trim();
    String discontinued = (discontinuedReq.isEmpty() ? null : discontinuedReq);
    // Manufacturer is always valid as soon as we have an option list
    // IdCpn
    String idCpnReq = request.getParameter(PARAM_IDCPN).trim();
    Long idCpn = (idCpnReq.isEmpty() ? null : Long.parseLong(idCpnReq));
    // NameCpn
    String nameCpn = (idCpn == null ? null : list.get(Integer.parseInt(idCpnReq)).getName());

    // Build a ComputerDto from each parameter
    return ComputerDto.builder().name(name).introduced(introduced).discontinued(discontinued)
        .idCpn(idCpn).nameCpn(nameCpn).build();
  }
}
