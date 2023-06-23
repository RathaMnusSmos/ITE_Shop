package kh.edu.rupp.ite.onlineshop.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SingleProduct implements Parcelable {
    private String id, sku, name, description, img_url;
    private int price;
    private double rating;
    public SingleProduct(){

    }
    public SingleProduct(String id, String sku, String name, String description, int price, String img_url, double rating){
        this.id = id;
        this.sku = sku;
        this.name =name;
        this.description = description;
        this.img_url = img_url;
        this.price = price;
        this.rating =rating;
    }

    protected SingleProduct(Parcel in) {
        id = in.readString();
        sku = in.readString();
        name = in.readString();
        description = in.readString();
        img_url = in.readString();
        price = in.readInt();
        rating = in.readDouble();
    }

    public static final Creator<SingleProduct> CREATOR = new Creator<SingleProduct>() {
        @Override
        public SingleProduct createFromParcel(Parcel in) {
            return new SingleProduct(in);
        }

        @Override
        public SingleProduct[] newArray(int size) {
            return new SingleProduct[size];
        }
    };

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImg_url() {
        return img_url;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(sku);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(img_url);
        parcel.writeInt(price);
        parcel.writeDouble(rating);
    }
}
