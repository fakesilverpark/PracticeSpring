package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.State;

public class ChangeOrderStateRequestDto {
    private State state;

    // dto 는 게터 미리 추가
    public State getState() {
        return this.state;
    }
}
