package com.memo.dto;

public class MemoDto {
    //controller와 service 간에 주고 받을 객체를 정의하며, 최종적으로는 view에 뿌려줄 객체
    //Entity와 속성이 같을 수 있으나, 여러 service를 거쳐야 하는 경우 dto의 몸집은 더 커짐
    //ex) AEntity에 a 속성, BEntity에 b속성이 있을 때, ZDto에 a,b 속성으로 정의될 수 있음
    //entity와 dto를 분리한 이유는 Entity는 DB 테이블이 정의되어 있으므로, 데이터 전달 목적을 갖는 객체인 dto를 정의하는 것이 좋다고 합니다. ( 참고 )
}
