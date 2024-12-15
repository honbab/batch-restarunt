package startspring.dto.menu;

import java.math.BigDecimal;

public record UpdateMenuDto(
        Long id,
        String menuName,
        BigDecimal menuPrice,
        String menuImgUrl
) {
}
