# Geekdemy 

### This repository contains the solution for Geekdemy written using Java 1.11 and build using Gradle.

<img width="860" alt="image" src="https://github.com/rakshitriya/Geekdemy/assets/140355188/d8214ca4-11f7-4c54-a00f-fbe908f5cc65">

## Problem Statement

#### Context
 Geekdemy provides a wide variety of online education programmes. Students can purchase them and enroll in these programmes. Geekdemy offers attractive discounts through their coupons so that students can spend less while purchasing these programmes.
 
#### Programmes
 There are 3 different categories of online programmes, and the cost is different for each category. A student can purchase any number of programmes at a time.
 
 - CERTIFICATION - Rs.3000 
 - DEGREE - Rs. 5000 
 - DIPLOMA - Rs 2500 
#### Coupons
 
 The discount coupons offered by Geekdemy are based on different criteria. Only one discount coupon can be applied at a time.
 
 - B4G1 - This coupon is applied automatically if 4 or more programmes are being purchased. The student gets one programme for free. The lowest priced programme is given for FREE.
 
 - DEAL_G20 - This coupon can be applied if the purchased programmes value is Rs.10,000/- or above. It provides a 20% discount on the total programme cost. The coupon needs to be applied explicitly to get a discount.
 
 - DEAL_G5 - This coupon can only be applied if there are a minimum of 2 programmes being purchased. It provides a 5% discount on the total programme cost. The coupon needs to be applied explicitly to get a discount.

 
#### Enrollment Fee
 If the total programme cost is below Rs. 6666/, an extra enrollment fee Rs.500/- is added to the cart. The enrollment fee is applied after the discount. If the total programme cost is or above Rs.6666/- the enrollment fee is waived off.
 
#### Pro Membership Fee
 A student can choose to purchase a Pro Membership for a small amount of Rs.200/- . The pro membership provides an additional membership discount on each of the individual programmes on top of the other discounts.
 
 - DIPLOMA - 1% discount 
 - CERTIFICATION - 2% discount 
 - DEGREE - 3% discount

### Assumptions
 - A student can add any number of programmes to the cart. 
 - A student can add the same category of programme multiple times. 
 - A student can choose to buy pro membership or not.  
 - If a student has  purchased a pro membership and has applied for a coupon, The coupon discount is applied after applying the pro membership discounts. 
 - The B4G1 coupon gets auto applied when there are more than 4 programmes in the cart. 
 - All the other coupons (DEAL_G20, DEAL_G5) need to be applied on the cart, if not no discount is provided. 
 - If there are 4 or more programmes in the cart and the student has applied for a coupon other than B4G1, B4G1 coupon will be used, and the other coupon needs to be ignored. 
-  If 2 or more coupons are applied, the higher value coupon needs to be considered (except in the case of 4 or more programmes; in that case B4G1 is auto applied).
	eg: if a student applies the coupon DEAL_G20 and DEAL_G5 and the purchase value is greater than 10,000, then DEAL_G20 needs to be considered.
	eg: if a student applies the coupon DEAL_G20 and DEAL_G5 and the purchase value is greater than 10,000, then DEAL_G20 needs to be considered. 
