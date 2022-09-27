package vn.sapo.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.sapo.config.Constants;
import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationUtils {

    public static List<Sort.Order> convertSortStringToListOrder(List<String> sort) {
        List<Sort.Order> orders = sort.stream().map(s -> {
            String[] order = s.split(",");
            String direction;
            if (order.length == 2) {
                direction = order[1];
            } else {
                direction = "asc";
            }
            return new Sort.Order(Sort.Direction.fromString(direction), order[0]);
        }).collect(Collectors.toList());

        if (!sort.contains("id,desc") && !sort.contains("id,asc")) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        }

        return orders;
    }


    public static Pageable limitPaging(Pageable pageable, Sort defaultSort) {
        Sort sort = pageable.getSortOr(defaultSort);
        int pageSize = pageable.getPageSize();
        if (pageSize <= 0 || pageSize > Constants.MAX_PAGE_SIZE) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }
        return PageRequest.of(pageable.getPageNumber(), pageSize, sort);
    }


    public static Pageable convertBasePaginationToPageable(BasePaginationFilterModel basePaginationFilterModel) {
        Sort sort = basePaginationFilterModel.getSort() == null || basePaginationFilterModel.getSort().size() == 0
            ? Sort.by(Sort.Direction.DESC, "id")
            : Sort.by(PaginationUtils.convertSortStringToListOrder(basePaginationFilterModel.getSort()));
        return PageRequest.of(basePaginationFilterModel.getPage() - 1, basePaginationFilterModel.getSize(), sort);
    }
}
