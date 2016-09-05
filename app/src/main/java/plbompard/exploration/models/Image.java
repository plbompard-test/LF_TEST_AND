package plbompard.exploration.models;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by plbompard on 01/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    @JsonProperty("label")
    @Nullable
    private String mLabel;

    @JsonProperty("612x344")
    @Nullable
    private String mUrl612_344;

    @JsonProperty("480x270")
    @Nullable
    private String mUrl480_270;

    @JsonProperty("240x135")
    @Nullable
    private String mUrl240_135;

    @JsonProperty("664x374")
    @Nullable
    private String mUrl664_374;

    @JsonProperty("1350x759")
    @Nullable
    private String mUrl1350_759;

    @JsonProperty("160x120")
    @Nullable
    private String mUrl160_120;

    @JsonProperty("80x60")
    @Nullable
    private String mUrl80_60;

    @JsonProperty("92x92")
    @Nullable
    private String mUrl92_92;

    @JsonProperty("184x184")
    @Nullable
    private String mUrl184_184;


    @Nullable
    public String getLabel() {
        return mLabel;
    }

    public void setLabel(@Nullable String label) {
        mLabel = label;
    }

    @Nullable
    public String getUrl612_344() {
        return mUrl612_344;
    }

    public void setUrl612_344(@Nullable String url612_344) {
        mUrl612_344 = url612_344;
    }

    @Nullable
    public String getUrl480_270() {
        return mUrl480_270;
    }

    public void setUrl480_270(@Nullable String url480_270) {
        mUrl480_270 = url480_270;
    }

    @Nullable
    public String getUrl240_135() {
        return mUrl240_135;
    }

    public void setUrl240_135(@Nullable String url240_135) {
        mUrl240_135 = url240_135;
    }

    @Nullable
    public String getUrl664_374() {
        return mUrl664_374;
    }

    public void setUrl664_374(@Nullable String url664_374) {
        mUrl664_374 = url664_374;
    }

    @Nullable
    public String getUrl1350_759() {
        return mUrl1350_759;
    }

    public void setUrl1350_759(@Nullable String url1350_759) {
        mUrl1350_759 = url1350_759;
    }

    @Nullable
    public String getUrl160_120() {
        return mUrl160_120;
    }

    public void setUrl160_120(@Nullable String url160_120) {
        mUrl160_120 = url160_120;
    }

    @Nullable
    public String getUrl80_60() {
        return mUrl80_60;
    }

    public void setUrl80_60(@Nullable String url80_60) {
        mUrl80_60 = url80_60;
    }

    @Nullable
    public String getUrl92_92() {
        return mUrl92_92;
    }

    public void setUrl92_92(@Nullable String url92_92) {
        mUrl92_92 = url92_92;
    }

    @Nullable
    public String getUrl184_184() {
        return mUrl184_184;
    }

    public void setUrl184_184(@Nullable String url184_184) {
        mUrl184_184 = url184_184;
    }

    @Nullable
    public String getBestQuality() {
        String best = getUrl1350_759();

        if (best == null) {
            best = getUrl664_374();
        }

        if (best == null) {
            best = getUrl612_344();
        }

        if (best == null) {
            best = getUrl480_270();
        }

        if (best == null) {
            best = getUrl240_135();
        }

        if (best == null) {
            best = getUrl184_184();
        }

        if (best == null) {
            best = getUrl160_120();
        }

        if (best == null) {
            best = getUrl92_92();
        }

        if (best == null) {
            best = getUrl80_60();
        }

        return best;
    }
}
