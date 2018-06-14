package sda.pl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    BigDecimal priceGross;
    BigDecimal priceNet;
    String priceSymbol;

    @Transient
    BigDecimal vat;

    public BigDecimal getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(BigDecimal priceGross) {
        this.priceGross = priceGross;
    }

    public BigDecimal getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(BigDecimal priceNet) {
        this.priceNet = priceNet;
    }

    public String getPriceSymbol() {
        return priceSymbol;
    }

    public void setPriceSymbol(String priceSymbol) {
        this.priceSymbol = priceSymbol;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
}
