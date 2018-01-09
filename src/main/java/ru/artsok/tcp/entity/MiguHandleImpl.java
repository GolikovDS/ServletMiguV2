package ru.artsok.tcp.entity;


import org.apache.log4j.Logger;
import ru.artsok.tcp.entity.interfaces.MiguHandle;
import ru.artsok.util.Caster;

import javax.servlet.http.HttpServlet;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

public class MiguHandleImpl extends HttpServlet implements MiguHandle {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());
    //    private Map<Integer, List<Integer>> miguMapOld = new HashMap<>();
    private static final Map<Integer, String> TEXT_EVENTS = new HashMap<>();
    private static final ThreadLocal<List<Integer>> threadLocal = new ThreadLocal<>();

    static {
        TEXT_EVENTS.put(1, "Неисправность сети 220В");
        TEXT_EVENTS.put(2, "Неисправность сети 24В");
        TEXT_EVENTS.put(3, "Неисправность АКБ");
        TEXT_EVENTS.put(4, "Аварийное выключение ШУ");
        TEXT_EVENTS.put(5, "АКБ разряжены");

        TEXT_EVENTS.put(7, "Нет связи с ПЛК");
        TEXT_EVENTS.put(8, "Ошибка связи с измерительными модулями");

        TEXT_EVENTS.put(11, "Неисправность цепи «Открыть ЗПУ»");
        TEXT_EVENTS.put(12, "Неисправность цепи «Закрыть ЗПУ»");
        TEXT_EVENTS.put(13, "Неисправность цепи «Открыто» крана с эл. пуском ЗПУ");
        TEXT_EVENTS.put(14, "Неисправность цепи «Закрыто» крана с эл. пуском ЗПУ");
        TEXT_EVENTS.put(15, "Неопределенное состояние крана с эл. пуском ЗПУ");

        TEXT_EVENTS.put(17, "Неисправность цепи положения ручного крана");
        TEXT_EVENTS.put(18, "Ручной кран ЗПУ закрыт");
        TEXT_EVENTS.put(19, "Неопределенное состояние ручного крана");
        TEXT_EVENTS.put(21, "Давление в побудительном баллоне Рпб < Рmin");
        TEXT_EVENTS.put(22, "Давление в побудительном баллоне Рпб > Рmax");
        TEXT_EVENTS.put(23, "ЗПУ не открывается (в течение 5 секунд подачи команды)");
        TEXT_EVENTS.put(24, "ЗПУ не закрывается (в течение 5 секунд подачи команды");


        TEXT_EVENTS.put(29, "Не введена масса порожнего резервуара");

        TEXT_EVENTS.put(31, "Не введено значение по каналу 1 (при включенном К1)");
        TEXT_EVENTS.put(32, "Не введено значение по каналу 2 (при включенном К2)");
        TEXT_EVENTS.put(33, "Не введено значение по каналу 3 (при включенном К3)");
        TEXT_EVENTS.put(34, "Не введено значение по каналу 4 (при включенном К4)");
        TEXT_EVENTS.put(35, "Не введено значение по каналу 5 (при включенном К5)");
        TEXT_EVENTS.put(36, "Не введено значение по каналу 6 (при включенном К6)");
        TEXT_EVENTS.put(37, "Не введено значение по каналу 7 (при включенном К7)");
        TEXT_EVENTS.put(38, "Не введено значение по каналу 8 (при включенном К8)");
        TEXT_EVENTS.put(39, "Не введено значение по каналу 9 (при включенном К9)");
        TEXT_EVENTS.put(40, "Не введено значение по каналу 10 (при включенном К10)");
        TEXT_EVENTS.put(41, "Канал 1 отключен (при введенном значении К1)");
        TEXT_EVENTS.put(42, "Канал 2 отключен (при введенном значении К2)");
        TEXT_EVENTS.put(43, "Канал 3 отключен (при введенном значении К3)");
        TEXT_EVENTS.put(44, "Канал 4 отключен (при введенном значении К4)");
        TEXT_EVENTS.put(45, "Канал 5 отключен (при введенном значении К5)");
        TEXT_EVENTS.put(46, "Канал 6 отключен (при введенном значении К6)");
        TEXT_EVENTS.put(47, "Канал 7 отключен (при введенном значении К7)");
        TEXT_EVENTS.put(48, "Канал 8 отключен (при введенном значении К8)");
        TEXT_EVENTS.put(49, "Канал 9 отключен (при введенном значении К9)");
        TEXT_EVENTS.put(50, "Канал 10 отключен (при введенном значении К10)а");
        TEXT_EVENTS.put(51, "Недостаточное количество ГОТВ для выпуска по каналу 1");
        TEXT_EVENTS.put(52, "Недостаточное количество ГОТВ для выпуска по каналу 2");
        TEXT_EVENTS.put(53, "Недостаточное количество ГОТВ для выпуска по каналу 3");
        TEXT_EVENTS.put(54, "Недостаточное количество ГОТВ для выпуска по каналу 4");
        TEXT_EVENTS.put(55, "Недостаточное количество ГОТВ для выпуска по каналу 5");
        TEXT_EVENTS.put(56, "Недостаточное количество ГОТВ для выпуска по каналу 6");
        TEXT_EVENTS.put(57, "Недостаточное количество ГОТВ для выпуска по каналу 7");
        TEXT_EVENTS.put(58, "Недостаточное количество ГОТВ для выпуска по каналу 8");
        TEXT_EVENTS.put(59, "Недостаточное количество ГОТВ для выпуска по каналу 9");
        TEXT_EVENTS.put(60, "Недостаточное количество ГОТВ для выпуска по каналу 10");
        TEXT_EVENTS.put(61, "Ошибка пуска по каналу 1: остаточный сигнал");
        TEXT_EVENTS.put(62, "Ошибка пуска по каналу 2: остаточный сигнал");
        TEXT_EVENTS.put(63, "Ошибка пуска по каналу 3: остаточный сигнал");
        TEXT_EVENTS.put(64, "Ошибка пуска по каналу 4: остаточный сигнал");
        TEXT_EVENTS.put(65, "Ошибка пуска по каналу 5: остаточный сигнал");
        TEXT_EVENTS.put(66, "Ошибка пуска по каналу 6: остаточный сигнал");
        TEXT_EVENTS.put(67, "Ошибка пуска по каналу 7: остаточный сигнал");
        TEXT_EVENTS.put(68, "Ошибка пуска по каналу 8: остаточный сигнал");
        TEXT_EVENTS.put(69, "Ошибка пуска по каналу 9: остаточный сигнал");
        TEXT_EVENTS.put(70, "Ошибка пуска по каналу 10: остаточный сигнал");
        TEXT_EVENTS.put(71, "Неисправность тензодатчика ТД1");
        TEXT_EVENTS.put(72, "Неисправность тензодатчика ТД2");
        TEXT_EVENTS.put(73, "Неисправность тензодатчика ТД3");
        TEXT_EVENTS.put(74, "Неисправность тензодатчика ТД4");

        TEXT_EVENTS.put(76, "Утечка ГОТВ 5%");
        TEXT_EVENTS.put(77, "Неравномерность распределения массы");
        TEXT_EVENTS.put(78, "Превышение максимального количества ГОТВ");

        TEXT_EVENTS.put(81, "Неисправность цепи реле низкого давления РД1.1");
        TEXT_EVENTS.put(82, "Неисправность цепи реле высокого давления РД 1.2");
        TEXT_EVENTS.put(83, "Неисправность цепи компрессора КМ1");
        TEXT_EVENTS.put(84, "Перегрузка цепи питания компрессора КМ1");

        TEXT_EVENTS.put(86, "Нет питания ХА1");
        TEXT_EVENTS.put(87, "Повышенное давление в нагнетательной линии ХА1");
        TEXT_EVENTS.put(88, "Недостаточное давление во всасывающей линии ХА1");

        TEXT_EVENTS.put(91, "Неисправность цепи реле низкого давления РД 2.1");
        TEXT_EVENTS.put(92, "Неисправность цепи реле высокого давления РД 2.2");
        TEXT_EVENTS.put(93, "Неисправность цепи компрессора КМ2");
        TEXT_EVENTS.put(94, "Перегрузка цепи питания компрессора КМ2");

        TEXT_EVENTS.put(96, "Нет питания ХА2");
        TEXT_EVENTS.put(97, "Повышенное давление в нагнетательной линии ХА2");
        TEXT_EVENTS.put(98, "Недостаточное давление во всасывающей линии ХА2");

        TEXT_EVENTS.put(101, "Неисправность цепи ПД1");
        TEXT_EVENTS.put(102, "Неисправность цепи ПД2");
        TEXT_EVENTS.put(103, "Давление ГОТВ повысилось >2,1 (3,1) МПа");

        TEXT_EVENTS.put(105, "Давление ГОТВ понизилось <1,9 (2,9) МПа");
        TEXT_EVENTS.put(106, "Ошибка показаний ПД1, ПД2");
        TEXT_EVENTS.put(107, "Неснижение давления ниже 2,05 (3,05) МПа");
        TEXT_EVENTS.put(108, "Неснижение давления до 2,0 (3,0) МПа");

        TEXT_EVENTS.put(111, "Неисправность цепи ЭН1 (ЭН1.1+ЭН1.2)");
        TEXT_EVENTS.put(112, "Неисправность цепи ЭН2 (ЭН2.1+ЭН2.2)");
        TEXT_EVENTS.put(113, "Системная ошибка");
        TEXT_EVENTS.put(114, "Системная ошибка");
        TEXT_EVENTS.put(115, "Системная ошибка");
        TEXT_EVENTS.put(116, "Системная ошибка");
        TEXT_EVENTS.put(117, "Системная ошибка");
        TEXT_EVENTS.put(118, "Системная ошибка");

        TEXT_EVENTS.put(121, "Неисправность цепи СДМПУ");
        TEXT_EVENTS.put(122, "Неисправность цепи СДПК");
        TEXT_EVENTS.put(123, "Срабатывание предохранительного клапана");
        TEXT_EVENTS.put(124, "Срабатывание МПУ (разрыв мембраны)");
        TEXT_EVENTS.put(130, "Недостаточно ГОТВ для работы ЭН");

        //Коды системных событий

        TEXT_EVENTS.put(131, "Включение ШУ");
        TEXT_EVENTS.put(132, "Выключение ШУ");
        TEXT_EVENTS.put(133, "Сигнал «Открыть ЗПУ»");
        TEXT_EVENTS.put(134, "Сигнал «Закрыть ЗПУ»");
        TEXT_EVENTS.put(135, "Кран с электропуском ЗПУ (КР2) открыт");
        TEXT_EVENTS.put(136, "Кран с электропуском ЗПУ (КР2) закрыт");
        TEXT_EVENTS.put(137, "Ручной кран ЗПУ (КР1) открыт");
        TEXT_EVENTS.put(138, "Пуск по каналу 1");
        TEXT_EVENTS.put(139, "Пуск по каналу 2");
        TEXT_EVENTS.put(140, "Пуск по каналу 3");
        TEXT_EVENTS.put(141, "Пуск по каналу 4");
        TEXT_EVENTS.put(142, "Пуск по каналу 5");
        TEXT_EVENTS.put(143, "Пуск по каналу 6");
        TEXT_EVENTS.put(144, "Пуск по каналу 7");
        TEXT_EVENTS.put(145, "Пуск по каналу 8");
        TEXT_EVENTS.put(146, "Пуск по каналу 9");
        TEXT_EVENTS.put(147, "Пуск по каналу 10");
        TEXT_EVENTS.put(148, "Включен ХА1");
        TEXT_EVENTS.put(149, "Выключен ХА1");
        TEXT_EVENTS.put(150, "Включен ХА2");
        TEXT_EVENTS.put(151, "Выключен ХА2");
        TEXT_EVENTS.put(152, "Дверь ШУ МИЖУ закрыта");
        TEXT_EVENTS.put(153, "Дверь ШУ МИЖУ открыта");
        TEXT_EVENTS.put(154, "Включен ЭН1");
        TEXT_EVENTS.put(155, "Выключен ЭН1");
        TEXT_EVENTS.put(156, "Включен ЭН2");
        TEXT_EVENTS.put(157, "Выключен ЭН2");
        TEXT_EVENTS.put(158, "Включен ручной режим ХА/ЭН");
        TEXT_EVENTS.put(159, "Включен автоматический режим ХА/ЭН");
        TEXT_EVENTS.put(160, "Автоматическая деблокировка ХА");
        TEXT_EVENTS.put(161, "Открыть ЗПУ");
        TEXT_EVENTS.put(162, "Закрыть ЗПУ");
        TEXT_EVENTS.put(163, "Введено (изменено) значение массы по каналу 1");
        TEXT_EVENTS.put(164, "Введено (изменено) значение массы по каналу 2");
        TEXT_EVENTS.put(165, "Введено (изменено) значение массы по каналу 3");
        TEXT_EVENTS.put(166, "Введено (изменено) значение массы по каналу 4");
        TEXT_EVENTS.put(167, "Введено (изменено) значение массы по каналу 5");
        TEXT_EVENTS.put(168, "Введено (изменено) значение массы по каналу 6");
        TEXT_EVENTS.put(169, "Введено (изменено) значение массы по каналу 7");
        TEXT_EVENTS.put(170, "Введено (изменено) значение массы по каналу 8");
        TEXT_EVENTS.put(171, "Введено (изменено) значение массы по каналу 9");
        TEXT_EVENTS.put(172, "Введено (изменено) значение массы по каналу 10");
        TEXT_EVENTS.put(173, "Автоматический ввод уставки по массе резервуара");
        TEXT_EVENTS.put(174, "Ручной ввод (контроль) уставки по массе резервуара");
        TEXT_EVENTS.put(175, "Автоматический ввод уставки по исходной массе ГОТВ");
        TEXT_EVENTS.put(176, "Ручной ввод (контроль) уставки по исходной массе ГОТВ");
        TEXT_EVENTS.put(177, "Команда снятия блокировки ХА1");
        TEXT_EVENTS.put(178, "Включить ХА1");
        TEXT_EVENTS.put(179, "Выключить ХА1");
        TEXT_EVENTS.put(180, "Команда снятия блокировки ХА2");
        TEXT_EVENTS.put(181, "Включить ХА2");
        TEXT_EVENTS.put(182, "Выключить ХА2");
        TEXT_EVENTS.put(183, "Включить ЭН1");
        TEXT_EVENTS.put(184, "Выключить ЭН1");
        TEXT_EVENTS.put(185, "Включить ЭН2");
        TEXT_EVENTS.put(186, "Выключить ЭН2");
        TEXT_EVENTS.put(187, "Изменение даты/времени");
        TEXT_EVENTS.put(188, "Изменение типоразмера МИЖУ");
        TEXT_EVENTS.put(189, "Инициализация памяти 2");
        TEXT_EVENTS.put(190, "Инициализация памяти 3");

        TEXT_EVENTS.put(240, "СПК: переполнение буфера событий");
        TEXT_EVENTS.put(241, "СПК: ошибка записи на диск");
        TEXT_EVENTS.put(242, "СПК: ошибка чтения с диска");
    }

    @Override
    public void view() {
    }

    public static ThreadLocal<List<Integer>> getThreadLocal() {
        return threadLocal;
    }

    public Migu removeMiguByNumberTreeView(String number) {
        Migu migu = miguMap.getMap().get(Integer.valueOf(number.substring(12)));
        miguMap.getMap().remove(Integer.valueOf(number.substring(12)));
        return migu;
    }


//    public void getMigu(String jabx) {
//        JAXBContext context = null;
//        MiguMap miguMap = null;
//        StringReader sr = new StringReader(jabx);
//        try {
//            context = JAXBContext.newInstance(MiguMap.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            miguMap = (MiguMap) unmarshaller.unmarshal(sr);
//            for (Migu migu : miguMap.getMap().values()) {
//                System.err.println(migu.toString());
//                MiguHandle.miguMap.getMap().put(migu.getNumber(), migu);
//
//                miguMapOld.put(migu.getNumber(), migu.getStates().getErrOrEventNumber());
//            }
//        } catch (JAXBException e) {
//            System.err.println("Ошибка unmarshaller JAXB - " + e.getMessage());
//        }
//    }

    public void getMigu(String jabx) {
        Caster caster = new Caster();
        if (jabx != null) {
            if (jabx.substring(0, 6).equals("<MIGU>")) {
                List<Integer> numbList = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(jabx.substring(13, jabx.indexOf("</COUNT>"))); i++) {
                    numbList.add(Integer.parseInt(jabx.substring(jabx.indexOf("<NUMB" + i + ">") + 6
                            + String.valueOf(i).length(), jabx.indexOf("</NUMB" + i + ">"))));
                }

                if (threadLocal.get() != null) {
                    for (Integer numb : threadLocal.get()) {
                        if (!numbList.contains(numb)) {
                            MiguHandle.miguMap.getMap().remove(numb);
                        }
                    }

                    threadLocal.set(numbList);

                } else {
                    System.out.println("Null inst");
                    threadLocal.set(numbList);
                }


                for (int i = 0; i < Integer.parseInt(jabx.substring(13, jabx.indexOf("</COUNT>"))); i++) {
                    Migu migu = new Migu();
                    MiguState state = new MiguState();
                    byte[] bytes = new byte[0x94];
                    migu.setNumber(Integer.parseInt(jabx.substring(jabx.indexOf("<NUMB" + i + ">") + 6
                            + String.valueOf(i).length(), jabx.indexOf("</NUMB" + i + ">"))));

                    migu.setNode(jabx.substring(jabx.indexOf("<NODE" + i + ">") + 6
                            + String.valueOf(i).length(), jabx.indexOf("</NODE" + i + ">")));
                    String data = jabx.substring(jabx.indexOf("<DATA" + i + ">") + 6
                            + String.valueOf(i).length(), jabx.indexOf("</DATA" + i + ">"));
                    int start = 0;
                    int end = 0;
                    state.setMiguIsRespond(false);
                    for (int j = 0; j < bytes.length; j++) {
                        end = start + 2;
                        bytes[j] = (byte) Integer.parseInt(data.substring(start, end), 16);
                        if (bytes[j] != 0)
                            state.setMiguIsRespond(true);
                        start = end;
                    }

//                state.setOldErrOrEventNumber(MiguHandle.miguMap.getMap().get(migu.getNumber()).getStates().getErrOrEventNumber());

                    //Time

                    state.setTime(DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, new Locale("ru", "RU")).format(
                            new Date((caster.bytesToLong(new byte[]{bytes[32], bytes[33], bytes[34], bytes[35]}) * 1000) - 10800000)));//-3 Hours

                    //Startup
                    boolean[] res = new boolean[10];
                    int over = 0x01;
                    for (int j = 0; j < res.length; j++) {
                        res[j] = ((int) ((caster.byteToLong(bytes[37]) << 8) | caster.byteToLong(bytes[36])) & over) == over;
                        over <<= 1;
                    }
                    state.setChannelsStartup(res);

                    //Pressure
                    state.setPressure(getValueFloat(0x14, bytes));
                    state.setFestPressureSensor(getValueFloat(0x16, bytes));
                    state.setSecondPressureSensor(getValueFloat(0x18, bytes));
                    //Mass
                    state.setMass(getValueFloat(0x1A, bytes));
                    state.setMassGOTV(getValueFloat(0x1C, bytes));
                    state.setDesiredMassOfEmptyPackaging(getValueFloat(0x1E, bytes));
                    state.setSetWeightGOTV(getValueFloat(0x20, bytes));
                    state.setValueFestSensor(getValueFloat(0x22, bytes));
                    state.setValueSecondSensor(getValueFloat(0x24, bytes));
                    state.setValueThirdSensor(getValueFloat(0x26, bytes));
                    state.setValueFourthSensor(getValueFloat(0x28, bytes));

                    state.setInstalledWeightGotvChannel(new float[]{
                            getValueFloat(0x2A, bytes), getValueFloat(0x2C, bytes), getValueFloat(0x2E, bytes),
                            getValueFloat(0x30, bytes), getValueFloat(0x32, bytes), getValueFloat(0x34, bytes),
                            getValueFloat(0x36, bytes), getValueFloat(0x38, bytes), getValueFloat(0x3A, bytes),
                            getValueFloat(0x3C, bytes)});

                    state.setLeakGOTV(getValueFloat(0x3E, bytes));

                    state.setNameDevice(new byte[]{bytes[0x88], bytes[0x89], bytes[0x8A], bytes[0x8B], bytes[0x8C], bytes[0x8D], bytes[0x8E], bytes[0x8F]});

                    state.setTypeDevice(bytes[0x90], bytes[0x91]);

                    List<Integer> list = getErrOrEvent0(bytes);
                    state.setErrOrEventNumber(list);
                    state.setStyleWeightGOTVChannel(getStyleWeightGOTVChannel(list));
                    state.setErrOrEvent(getErrOrEvent(bytes));
                    migu.setStates(state);
                    MiguHandle.miguMap.getMap().put(migu.getNumber(), migu);
//                miguMapOld.put(migu.getNumber(), migu.getStates().getErrOrEventNumber());
                    System.err.println(migu.toString());
                }
            }
        }
    }


    private float getValueFloat(int numberStartByte, byte[] bytes) {
        numberStartByte *= 2;
        return new Caster().bytesToFloat(new byte[]{bytes[numberStartByte], bytes[numberStartByte + 1],
                bytes[numberStartByte + 2], bytes[numberStartByte + 3]});
    }

    private List<Integer> getErrOrEvent0(byte[] bytes) {
        List<Integer> result = new ArrayList<>();
        boolean[] flag;
        for (int i = 0; i < 32; i++) {
            flag = getFlags(bytes[i]);
            for (int j = 0; j < 8; j++) {
                if (flag[j]) {
                    result.add(i * 8 + j);
                }
            }
        }
        return result;
    }

    public boolean[] getFlags(byte b) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; i++) {
            result[i] = ((b >>> i) & 0x01) == 1;
        }
        return result;
    }

    public String[] getStyleWeightGOTVChannel(List<Integer> list) {
        String[] result = new String[10];

        for (int i = 0; i < 10; i++) {
            if (list.contains(51 + i) || list.contains(61 + i) || list.contains(138 + i)) {
                result[i] = "red;";
            } else if (list.contains(31 + i) || list.contains(41 + i)) {
                result[i] = "yellow";
            } else {
                result[i] = "";
            }
        }
        return result;
    }

    public List<String> getErrOrEvent(byte[] bytes) {
        List<Integer> lists = getErrOrEvent0(bytes);
        List<String> stringList = new ArrayList<>();
        stringList.addAll(lists.stream().map(
                integer -> String.format("E%03d \t", integer) + TEXT_EVENTS.get(integer)).collect(Collectors.toList()));
        return stringList;
    }

}
