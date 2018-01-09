package ru.artsok.tcp.entity;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MiguState {
    private boolean miguIsRespond;
    private boolean isSelect;
    private String time;
    private boolean[] channelsStartup = new boolean[10];

    private float pressure;
    private float festPressureSensor;
    private float secondPressureSensor;
    private float mass;
    private float massGOTV;
    private float desiredMassOfEmptyPackaging;
    private float setWeightGOTV;
    private float valueFestSensor;
    private float valueSecondSensor;
    private float valueThirdSensor;
    private float valueFourthSensor;
    private float[] installedWeightGotvChannel;
    private float leakGOTV;

    private String nameDevice;
    private String typeDevice;

    private List<String> ErrOrEvent = new ArrayList<>();
    private List<Integer> ErrOrEventNumber = new ArrayList<>();

    private String[] styleWeightGOTVChannel = new String[10];
    private String oldAjaxGetUserServletResponseText;
    private String oldAjaxGetUserServletResponseImgText;
    private String oldAjaxGetUserServletResponseEventText;

    public List<Integer> getErrOrEventNumber() {
        return ErrOrEventNumber;
    }


    public void setErrOrEventNumber(List<Integer> errOrEventNumber) {
        ErrOrEventNumber = errOrEventNumber;
    }

    public List<String> getErrOrEvent() {
        return ErrOrEvent;
    }


    public void setErrOrEvent(List<String> errOrEvent) {
        ErrOrEvent = errOrEvent;
    }


    public void setTypeDevice(String typeDevice) {
        this.typeDevice = typeDevice;
    }


    public void setTypeDevice(byte maxPressure, byte maxMass) {
        StringBuilder builder = new StringBuilder();

        if (maxPressure == 0)
            builder.append("2,2/");
        else
            builder.append("3,3/");

        switch (maxMass) {
            case 0:
                builder.append("3");
                break;
            case 1:
                builder.append("5");
                break;
            case 2:
                builder.append("10");
                break;
            case 3:
                builder.append("16");
                break;
            case 4:
                builder.append("25");
                break;
            case 5:
                builder.append("28");
                break;
        }
        typeDevice = builder.toString();
    }

    public String getTypeDevice() {
        return typeDevice;
    }


    public MiguState() {
//        value.put("pressure", 0.0f);
//        value.put("festPressureSensor", 0.0f);
//        value.put("secondPressureSensor", 0.0f);
//        value.put("mass", 0.0f);
//        value.put("massGOTV", 0.0f);
//        value.put("desiredMassOfEmptyPackaging", 0.0f);
//        value.put("setWeightGOTV", 0.0f);
//        value.put("valueFestSensor", 0.0f);
//        value.put("valueSecondSensor", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);
//        value.put("pressure", 0.0f);

    }


    public float getDesiredMassOfEmptyPackaging() {
        return desiredMassOfEmptyPackaging;
    }


    public void setDesiredMassOfEmptyPackaging(float desiredMassOfEmptyPackaging) {
        this.desiredMassOfEmptyPackaging = desiredMassOfEmptyPackaging;
    }


    public float getLeakGOTV() {
        return leakGOTV;
    }


    public void setLeakGOTV(float leakGOTV) {
        this.leakGOTV = leakGOTV;
    }

    public float getMassGOTV() {
        return massGOTV;
    }


    public void setMassGOTV(float massGOTV) {
        this.massGOTV = massGOTV;
    }

    public String getNameDevice() {
        return nameDevice;
    }


    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public void setNameDevice(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (bytes[i] != 0x00) {
                try {
                    sb.append(new String(new byte[]{bytes[i]}, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        this.nameDevice = sb.toString();
    }

    public float getSetWeightGOTV() {
        return setWeightGOTV;
    }


    public void setSetWeightGOTV(float setWeightGOTV) {
        this.setWeightGOTV = setWeightGOTV;
    }

    public float getValueFestSensor() {
        return valueFestSensor;
    }


    public void setValueFestSensor(float valueFestSensor) {
        this.valueFestSensor = valueFestSensor;
    }

    public float getValueFourthSensor() {
        return valueFourthSensor;
    }


    public void setValueFourthSensor(float valueFourthSensor) {
        this.valueFourthSensor = valueFourthSensor;
    }

    public float getValueSecondSensor() {
        return valueSecondSensor;
    }


    public void setValueSecondSensor(float valueSecondSensor) {
        this.valueSecondSensor = valueSecondSensor;
    }

    public float getValueThirdSensor() {
        return valueThirdSensor;
    }


    public void setValueThirdSensor(float valueThirdSensor) {
        this.valueThirdSensor = valueThirdSensor;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public float getFestPressureSensor() {
        return festPressureSensor;
    }


    public void setFestPressureSensor(float festPressureSensor) {
        this.festPressureSensor = festPressureSensor;
    }

    public float getSecondPressureSensor() {
        return secondPressureSensor;
    }


    public void setSecondPressureSensor(float secondPressureSensor) {
        this.secondPressureSensor = secondPressureSensor;
    }


    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public MiguState(boolean miguIsRespond) {
        this.miguIsRespond = miguIsRespond;
    }

    public boolean isMiguIsRespond() {
        return miguIsRespond;
    }

    public boolean[] getChannelsStartup() {
        return channelsStartup;
    }

    public float getPressure() {
        return pressure;
    }


    public void setPressure(float pressure) {
        this.pressure = pressure;
    }


    public void setChannelsStartup(boolean[] channelsStartup) {
        this.channelsStartup = channelsStartup;
    }


    public void setMiguIsRespond(boolean miguIsRespond) {
        this.miguIsRespond = miguIsRespond;
    }

    public float getMass() {
        return mass;
    }


    public void setMass(float mass) {
        this.mass = mass;
    }

    public float[] getInstalledWeightGotvChannel() {
        return installedWeightGotvChannel;
    }

    public void setInstalledWeightGotvChannel(float[] installedWeightGotvChannel) {
        this.installedWeightGotvChannel = installedWeightGotvChannel;
    }

    public String[] getStyleWeightGOTVChannel() {
        return styleWeightGOTVChannel;
    }

    public void setStyleWeightGOTVChannel(String[] styleWeightGOTVChannel) {
        this.styleWeightGOTVChannel = styleWeightGOTVChannel;
    }

    public String getOldAjaxGetUserServletResponseText() {
        return oldAjaxGetUserServletResponseText;
    }

    public void setOldAjaxGetUserServletResponseText(String oldAjaxGetUserServletResponseText) {
        this.oldAjaxGetUserServletResponseText = oldAjaxGetUserServletResponseText;
    }

    public String getOldAjaxGetUserServletResponseImgText() {
        return oldAjaxGetUserServletResponseImgText;
    }

    public void setOldAjaxGetUserServletResponseImgText(String oldAjaxGetUserServletResponseImgText) {
        this.oldAjaxGetUserServletResponseImgText = oldAjaxGetUserServletResponseImgText;
    }

    public String getOldAjaxGetUserServletResponseEventText() {
        return oldAjaxGetUserServletResponseEventText;
    }

    public void setOldAjaxGetUserServletResponseEventText(String oldAjaxGetUserServletResponseEventText) {
        this.oldAjaxGetUserServletResponseEventText = oldAjaxGetUserServletResponseEventText;
    }

    @Override
    public String toString() {
        return "MiguState{" +
                "channelsStartup=" + Arrays.toString(channelsStartup) +
                ", miguIsRespond=" + miguIsRespond +
                ", isSelect=" + isSelect +
                ", time='" + time + '\'' +
                ", pressure=" + pressure +
                ", festPressureSensor=" + festPressureSensor +
                ", secondPressureSensor=" + secondPressureSensor +
                ", mass=" + mass +
                ", massGOTV=" + massGOTV +
                ", desiredMassOfEmptyPackaging=" + desiredMassOfEmptyPackaging +
                ", setWeightGOTV=" + setWeightGOTV +
                ", valueFestSensor=" + valueFestSensor +
                ", valueSecondSensor=" + valueSecondSensor +
                ", valueThirdSensor=" + valueThirdSensor +
                ", valueFourthSensor=" + valueFourthSensor +
                ", leakGOTV=" + leakGOTV +
                ", nameDevice='" + nameDevice + '\'' +
                ", typeDevice='" + typeDevice + '\'' +
                ", ErrOrEvent=" + ErrOrEvent +
                '}';
    }

    public String toStringHtml() {
        return "MiguState{" +
                "channelsStartup=" + Arrays.toString(channelsStartup) + "</br" +
                ", miguIsRespond=" + miguIsRespond + "</br" +
                ", isSelect=" + isSelect + "</br" +
                ", time='" + time + '\'' + "</br" +
                ", pressure=" + pressure + "</br" +
                ", festPressureSensor=" + festPressureSensor + "</br" +
                ", secondPressureSensor=" + secondPressureSensor + "</br" +
                ", mass=" + mass + "</br" +
                ", massGOTV=" + massGOTV + "</br" +
                ", desiredMassOfEmptyPackaging=" + desiredMassOfEmptyPackaging + "</br" +
                ", setWeightGOTV=" + setWeightGOTV + "</br" +
                ", valueFestSensor=" + valueFestSensor + "</br" +
                ", valueSecondSensor=" + valueSecondSensor + "</br" +
                ", valueThirdSensor=" + valueThirdSensor + "</br" +
                ", valueFourthSensor=" + valueFourthSensor + "</br" +
                ", leakGOTV=" + leakGOTV + "</br" +
                ", nameDevice='" + nameDevice + '\'' + "</br" +
                ", typeDevice='" + typeDevice + '\'' +
                ", ErrOrEvent=" + ErrOrEvent +
                '}';
    }
}
