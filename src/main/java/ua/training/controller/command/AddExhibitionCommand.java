package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService = new ExhibitionService();
    private static final String THEME = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{2,}$";
    private static final String THEME_UA = "[А-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9\\']{2,}";
    private static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]{1,}";

    @Override
    public String execute(HttpServletRequest request) {
        String error = "";
        int price;
        Map<String, String> map = new HashMap<>();
        String theme = request.getParameter("theme");
        String themeUk = request.getParameter("themeUk");
        String description = request.getParameter("description");
        String descriptionUk = request.getParameter("descriptionUk");
        String imageUrl = request.getParameter("imageUrl");
        String date = request.getParameter("date");
        LocalDate ld = LocalDate.parse(date);
        try {
            price = Integer.parseInt(request.getParameter("price"));
        }catch (NumberFormatException e){
            error = "invalidPrice";
            request.getSession().setAttribute("error", error);
            return "/admin/adminbasis.jsp";
        }
        if(ld.isBefore(LocalDate.now())){
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return "/admin/adminbasis.jsp";
        }
        map.put(theme, THEME);
        map.put(themeUk, THEME_UA);
        map.put(description, FIRST_NAME_REG);
        map.put(descriptionUk, LAST_NAME_REG);
        map.put(imageUrl, PASSWORD_REG);
        for (String str : map.keySet()) {
            if (!str.matches(map.get(str))) {
                request.getSession().setAttribute("error", error);
                return "/admin/adminbasis.jsp";
            }
        }
        Exhibition exhibition = Exhibition.builder()
                .themeUk(themeUk)
                .descriptionUk(descriptionUk)
                .theme(theme)
                .date(ld)
                .price(price)
                .imageUrl(imageUrl)
                .build();
        System.out.println(exhibition);
        return "redirect:/success.jsp";
    }

}
