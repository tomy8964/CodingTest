SELECT product_code, sum(SALES_AMOUNT*PRICE) as SALES
from PRODUCT
inner join OFFLINE_SALE 
USING(product_id)
group by product_code
order by SALES DESC, PRODUCT_CODE ASC;
#PRODUCT 테이블과 OFFLINE_SALE 을 inner join해주고 product_id를 기준으로
#SELECT에 출력할컬럼 작성해주고
#group by 에 product_code 기준으로
#order by에 내림차순 오름차순 정리해주면끝