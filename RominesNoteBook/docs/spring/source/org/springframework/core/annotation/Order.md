# Order
```
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHON,ElementType.FIELD})
@Documented
public @interface Order {
    int value() default 2147483647;
}
```