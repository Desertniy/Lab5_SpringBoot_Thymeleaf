package Desertniy.Lab5.model;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * PropertyBody
 */
@Validated
public class PropertyBody   {
  private String street = null;

  private BigDecimal price = null;

  private Integer monthlyRent = null;

  public PropertyBody() {
  }

  public PropertyBody(String street, BigDecimal price, Integer monthlyRent) {
    this.street = street;
    this.price = price;
    this.monthlyRent = monthlyRent;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getMonthlyRent() {
    return monthlyRent;
  }

  public void setMonthlyRent(Integer monthlyRent) {
    this.monthlyRent = monthlyRent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PropertyBody propertyBody = (PropertyBody) o;
    return Objects.equals(this.street, propertyBody.street) &&
        Objects.equals(this.price, propertyBody.price) &&
        Objects.equals(this.monthlyRent, propertyBody.monthlyRent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, price, monthlyRent);
  }

  @Override
  public String toString() {

    String sb = "{\n" +
            "    street: " + toIndentedString(street) + "\n" +
            "    price: " + toIndentedString(price) + "\n" +
            "    monthlyRent: " + toIndentedString(monthlyRent) + "\n" +
            "}";
    return sb;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
