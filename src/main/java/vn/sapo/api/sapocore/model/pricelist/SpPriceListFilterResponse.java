package vn.sapo.api.sapocore.model.pricelist;

import vn.sapo.api.sapocore.model.SpBasePagingFilterResponse;

import java.util.List;

public class SpPriceListFilterResponse extends SpBasePagingFilterResponse {
    private List<SpPriceList> priceLists;

    public List<SpPriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<SpPriceList> priceLists) {
        this.priceLists = priceLists;
    }
}
