package com.cookandroid.block7

// Event_invasion_robbery: 침입 - 강도
class Event_invasion_firefighter(GameActivity: GameActivity, eventName: String, weight: Int, isAvailable: Boolean)
    : Event(GameActivity, eventName, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        setPreScript(getScript("event_invasion_firefighter_pre"))
        setPostScript(getScript("event_invasion_firefighter_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(tmp: Int) {
        if(tmp == 1) { // 문을 열어준 경우
            hurtRandomMember() // 안에 있는 멤버중 랜덤으로 1명이 다침
            loseRandomFood(1, 3) // 김밥, 물 중 하나를 골라 랜덤으로 1~3개를 잃음
        } else { // 문을 열어주지 않은 경우
            setPreScript(getScript("event_invasion_firefighter_post_tmp"))
        }
    }
}
