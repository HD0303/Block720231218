package com.cookandroid.block7

class Evnet_plunder_oldMan(GameActivity: GameActivity, eventName: String, weight: Int, isAvailable: Boolean) :
    Event(GameActivity, eventName, weight, isAvailable) {

    // 생성자 - 기본 preScript, postScript 설정
    init {
        super.setPreScript(getScript("evnet_plunder_oldMan_pre"))
        super.setPostScript(getScript("evnet_plunder_oldMan_post"))
    }

    // isAvailable, weight 수정 메소드
    override fun setIsAvailable() {
        isAvailable = true
    }

    // 이벤트 효과 메소드
    override fun eventEffect(tmp: Int) {
        if(tmp == 1) { // 문을 열어준 경우

        } else { // 문을 열어주지 않은 경우
            setPreScript(getScript("evnet_plunder_oldMan_post_tmp"))
        }
    }
}