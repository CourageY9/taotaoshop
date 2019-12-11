package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/10:16:33  <br/>
 * Description:
 */
public class SearchItem implements Serializable {

    private Long id;
    private String title;
    private String sellPoint;
    private Long price;
    private String image;
    private String categoryName;
    private String itemDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String[] getImages(){
        if (image!=null && !"".equals(image)){
            String[] imgs = image.split(",");
            return imgs;
        }
        return null;
    }
}
