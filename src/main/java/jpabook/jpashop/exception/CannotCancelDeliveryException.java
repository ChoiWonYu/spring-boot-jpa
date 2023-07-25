package jpabook.jpashop.exception;

public class CannotCancelDeliveryException extends RuntimeException {

  public CannotCancelDeliveryException() {
    super();
  }

  public CannotCancelDeliveryException(String message) {
    super(message);
  }

  public CannotCancelDeliveryException(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotCancelDeliveryException(Throwable cause) {
    super(cause);
  }
}
