package startspring.dto.menu;

import java.math.BigDecimal;

public record RegisterMenuDto(
        String menuName,
        BigDecimal menuPrice,
        String menuImgUrl,
        boolean useMenu
) {
}
