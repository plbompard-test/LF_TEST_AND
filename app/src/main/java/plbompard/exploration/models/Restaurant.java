package plbompard.exploration.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by plbompard on 01/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @JsonProperty("id_restaurant")
    private int mId;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("description")
    private String mDescription;

    @JsonProperty("portal_url")
    private String mPortalUrl;

    @JsonProperty("gps_lat")
    private long mLat;

    @JsonProperty("gps_long")
    private long mLong;

    @JsonProperty("address")
    private String mAddress;

    @JsonProperty("id_city")
    private int mCityId;

    @JsonProperty("city")
    private String mCityName;

    @JsonProperty("id_city_zipcode")
    private int mCityZipCodeId;

    @JsonProperty("zipcode")
    private String mZipcode;

    @JsonProperty("id_country")
    private int mCountryId;

    @JsonProperty("phone")
    private String mPhone;

    @JsonProperty("email")
    private String mEmail;

    @JsonProperty("avg_rate")
    private float mAverageRate;

    @JsonProperty("rate_count")
    private int mRateCount;

    @JsonProperty("pics_main")
    private Image mMainImage;

    @JsonProperty("pics_diaporama")
    private List<Image> mImageDiaporama;

    @JsonProperty("ratings")
    private Ratings mRatings;

    @JsonProperty("speciality")
    private String mSpeciality;

    @JsonProperty("card_price")
    private int mPrice;

    @JsonProperty("currency_code")
    private String mCurrencyCode;


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPortalUrl() {
        return mPortalUrl;
    }

    public void setPortalUrl(String portalUrl) {
        mPortalUrl = portalUrl;
    }

    public long getLat() {
        return mLat;
    }

    public void setLat(long lat) {
        mLat = lat;
    }

    public long getLong() {
        return mLong;
    }

    public void setLong(long aLong) {
        mLong = aLong;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getCityId() {
        return mCityId;
    }

    public void setCityId(int cityId) {
        mCityId = cityId;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public int getCityZipCodeId() {
        return mCityZipCodeId;
    }

    public void setCityZipCodeId(int cityZipCodeId) {
        mCityZipCodeId = cityZipCodeId;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
        mZipcode = zipcode;
    }

    public int getCountryId() {
        return mCountryId;
    }

    public void setCountryId(int countryId) {
        mCountryId = countryId;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getRateCount() {
        return mRateCount;
    }

    public void setRateCount(int rateCount) {
        mRateCount = rateCount;
    }

    public Image getMainImage() {
        return mMainImage;
    }

    public void setMainImage(Image mainImage) {
        mMainImage = mainImage;
    }

    public Ratings getRatings() {
        return mRatings;
    }

    public void setRatings(Ratings ratings) {
        mRatings = ratings;
    }

    public String getSpeciality() {
        return mSpeciality;
    }

    public void setSpeciality(String speciality) {
        mSpeciality = speciality;
    }

    public List<Image> getImageDiaporama() {
        return mImageDiaporama;
    }

    public void setImageDiaporama(List<Image> imageDiaporama) {
        mImageDiaporama = imageDiaporama;
    }

    public float getAverageRate() {
        return mAverageRate;
    }

    public void setAverageRate(float averageRate) {
        mAverageRate = averageRate;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Utils
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private String getCurrencySymbolFor(String currencyCode) {
        switch (currencyCode) {
            case "EUR":
                return "€";
            case "USD":
                return "$";
        }

        //Did not match any, let the currencyCode instead.
        return currencyCode;
    }

    public String getSpecialityWithPrice() {
        String speciality = getSpeciality();
        int price = getPrice();
        String currencyCode = getCurrencyCode();
        StringBuilder sb = new StringBuilder();
        sb.append(speciality)
                .append("  ")
                .append(price)
                .append(getCurrencySymbolFor(currencyCode));

        return sb.toString();
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAddress())
                .append(" ")
                .append(getZipcode())
                .append(" ")
                .append(getCityName());
        return sb.toString();
    }
}
