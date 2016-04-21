package com.excilys.computerdatabase.mapping.request;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ComputerRequestMapper {

  private static final String PARAM_ID = "id";
  private static final String PARAM_NAME = "name";
  private static final String PARAM_INTRODUCED = "introduced";
  private static final String PARAM_DISCONTINUED = "discontinued";
  private static final String PARAM_IDCPN = "idCpn";

  /**
   * Mapping a request (post) to a ComputerDto.
   * 
   * @param list
   * 
   *          TODO : Séparation du mapping de compagnie et de computer
   * 
   * @return a ComputerDto from the request
   */
  public static ComputerDto toDto(HttpServletRequest request, List<Company> list) {
    // Id
    String idReq = request.getParameter(PARAM_ID);
    Long id = (idReq == null || idReq.trim().isEmpty() ? null : Long.parseLong(idReq.trim()));
    // Name
    String nameReq = request.getParameter(PARAM_NAME);
    String name = (nameReq == null || nameReq.trim().isEmpty() ? null : nameReq.trim());
    // Introduced
    String introducedReq = request.getParameter(PARAM_INTRODUCED);
    String introduced = (introducedReq == null || introducedReq.trim().isEmpty() ? null
        : introducedReq.trim());
    // Discontinued
    String discontinuedReq = request.getParameter(PARAM_DISCONTINUED);
    String discontinued = (discontinuedReq == null || discontinuedReq.trim().isEmpty() ? null
        : discontinuedReq.trim());
    // Manufacturer is always valid as soon as we have an option list
    // IdCpn
    String idCpnReq = request.getParameter(PARAM_IDCPN);
    Long idCpn = (idCpnReq == null || idCpnReq.trim().isEmpty() ? null
        : Long.parseLong(idCpnReq.trim()));
    // NameCpn
    String nameCpn = (idCpn == null ? null : list.get(Integer.parseInt(idCpnReq.trim())).getName());

    // Build a ComputerDto from each parameter
    return ComputerDto.builder().id(id).name(name).introduced(introduced).discontinued(discontinued)
        .idCpn(idCpn).nameCpn(nameCpn).build();
  }
}
