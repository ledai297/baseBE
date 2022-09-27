package vn.sapo.vm.standardproduct;

import vn.sapo.vm.pagination.BasePaginationFilterResultModel;

import java.util.List;

public class StandardProductFilterResultModel extends BasePaginationFilterResultModel {
    private List<ProductModel> products;
    private List<ProductReference.Brand> brands;
    private List<ProductReference.Country> countries;
    private List<ProductReference.Producer> producers;
    private List<ProductReference.Attribute> attributes;

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public List<ProductReference.Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<ProductReference.Brand> brands) {
        this.brands = brands;
    }

    public List<ProductReference.Country> getCountries() {
        return countries;
    }

    public void setCountries(List<ProductReference.Country> countries) {
        this.countries = countries;
    }

    public List<ProductReference.Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<ProductReference.Producer> producers) {
        this.producers = producers;
    }

    public List<ProductReference.Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductReference.Attribute> attributes) {
        this.attributes = attributes;
    }
}
