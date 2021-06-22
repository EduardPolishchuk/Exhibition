package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UpdateExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService = new ExhibitionService();
    private static final String EN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{2,}$";
    private static final String UA_REGEX = "[А-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9\\']{2,}";
    private static final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    public static final String DATE_REGEX = "^(19|20)\\d\\d[- .](0[1-9]|1[012])[- .](0[1-9]|[12][0-9]|3[01])$";

    @Override

    public String execute(HttpServletRequest request) {
        String ifError = "/admin/adminExhibitionView.jsp";
        String error = "";
        int price;
        int id = Integer.parseInt(request.getParameter("exId"));
        Map<String, String> map = new HashMap<>();
        String theme = request.getParameter("theme");
        String themeUk = request.getParameter("themeUk");
        String description = request.getParameter("description");
        String descriptionUk = request.getParameter("descriptionUk");
        String imageUrl = request.getParameter("imageUrl");
        String date = request.getParameter("date");
        LocalDate ld;
        try {
            price = Integer.parseInt(request.getParameter("price"));
        } catch (NumberFormatException e) {
            error = "invalidPrice";
            request.getSession().setAttribute("error", error);
            return ifError;
        }
        map.put(theme, EN_REGEX);
        map.put(themeUk, UA_REGEX);
        map.put(description, EN_REGEX);
        map.put(descriptionUk, UA_REGEX);
        map.put(imageUrl, URL_REGEX);
        map.put(date, DATE_REGEX);
        for (String str : map.keySet()) {
            if (str == null || !str.matches(map.get(str))) {
                request.getSession().setAttribute("error", error);
                return ifError;
            }
        }
        ld = LocalDate.parse(date);
        System.out.println(date);
        if (ld.isBefore(LocalDate.now())) {
            System.out.println("DATE");
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return ifError;
        }
        Exhibition exhibition = Exhibition.builder()
                .id(id)
                .themeUk(themeUk)
                .descriptionUk(descriptionUk)
                .description(description)
                .theme(theme)
                .date(ld)
                .price(price)
                .imageUrl(imageUrl)
                .build();
        if (!exhibitionService.update(exhibition)) {
            System.out.println("NOT DATE");
            ifError = "/admin/adminbasis.jsp";
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return ifError;
        }
        return "redirect:/admin/adminExhibitionView?exId="+id;
    }

}
