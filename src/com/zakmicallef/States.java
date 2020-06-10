package com.zakmicallef;

import java.util.HashMap;
import java.util.Map;

import static com.zakmicallef.States.State.*;
import static com.zakmicallef.States.State.ERR;

public class States {

    public static State[][] TransitionTable = {  /*
            Dgit DecP Alpha Prnt Star FwSl PlMn Ineq Excl Eqls UndS Quot Pnct NewL EofF Othr  */
            {S01, ERR, S04, ERR, S13, S07, S14, S15, S17, S16, S04, S05, S19, ERR, S20, ERR}, /*S00*/
            {S01, S02, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S01*/
            {S03, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S02*/
            {S03, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S03*/
            {S04, ERR, S04, ERR, ERR, ERR, ERR, ERR, ERR, ERR, S04, ERR, ERR, ERR, ERR, ERR}, /*S04*/
            {S05, S05, S05, S05, S05, S05, S05, S05, S05, S05, S05, S06, S05, ERR, ERR, ERR}, /*S05*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S06*/
            {ERR, ERR, ERR, ERR, S10, S08, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S07*/
            {S08, S08, S08, S08, S08, S08, S08, S08, S08, S08, S08, S08, S08, S09, ERR, ERR}, /*S08*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S09*/
            {S10, S10, S10, S10, S11, S10, S10, S10, S10, S10, S10, S10, S10, S10, ERR, ERR}, /*S10*/
            {S10, S10, S10, S10, S11, S12, S10, S10, S10, S10, S10, S10, S10, S10, ERR, ERR}, /*S11*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S12*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S13*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S14*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, S18, ERR, ERR, ERR, ERR, ERR, ERR}, /*S15*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, S18, ERR, ERR, ERR, ERR, ERR, ERR}, /*S16*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, S18, ERR, ERR, ERR, ERR, ERR, ERR}, /*S17*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S18*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}, /*S19*/
            {ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR, ERR}  /*S20*/
    };

    public static enum State {
        S00(0),
        S01(1),
        S02(2),
        S03(3),
        S04(4),
        S05(5),
        S06(6),
        S07(7),
        S08(8),
        S09(9),
        S10(10),
        S11(11),
        S12(12),
        S13(13),
        S14(14),
        S15(15),
        S16(16),
        S17(17),
        S18(18),
        S19(19),
        S20(20),
        ERR(21),
        DEF(22);


        private int value;
        private static Map map = new HashMap<>();


        private State(int value) {
            this.value = value;
        }

        static {
            for (State state : State.values()) {
                map.put(state.value, state);
            }
        }

        public static State valueOf(int state) {
            return (State) map.get(state);
        }

        public int getValue() {
            return value;
        }

    }

    static boolean isFinal(State state) {
        switch (state) {
            case S01:
            case S03:
            case S04:
            case S06:
            case S07:
            case S09:
            case S12:
            case S13:
            case S14:
            case S15:
            case S16:
            case S18:
            case S19:
            case S20:
                return true;
            default:
                return false;
        }
    }




}
