
package com.example.choco3.rxandretrofit.Model;



import com.google.gson.annotations.SerializedName;


public class Wind {

    @SerializedName("speed")
    private Double speed;
    @SerializedName("deg")
    private Integer deg;
    @SerializedName("gust")
    private Double gust;

    /**
     * 
     * @return
     *     The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    /**
     * 
     * @return
     *     The gust
     */
    public Double getGust() {
        return gust;
    }

    /**
     * 
     * @param gust
     *     The gust
     */
    public void setGust(Double gust) {
        this.gust = gust;
    }

}
