package com.company;

import javax.xml.bind.JAXB;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {

    static final String pathURL ="http://gencat.cat/llengua/cinema/provacin.xml";
    static List<Film> films;

    public static void main(String[] args)  {
        Menu menu = new Menu();
        try {
            URL url = new URL(pathURL);
            films= JAXB.unmarshal(url, Films.class).filmList;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch (menu.menu()){
            case 1:
                switch (menu.camps_buscar()){
                    case 1:
                        int buscarId = new Scanner(System.in).nextInt();
                        films.stream().filter(line -> line.getIdfilm()==buscarId).forEach(System.out::println);
                        break;
                    case 2:
                        String buscarTitol = new Scanner(System.in).nextLine();
                        films.stream().filter(line -> line.getTitol().equals(buscarTitol)).forEach(System.out::println);
                        break;
                    default:
                }
                break;
            case 2:
                switch (menu.quantitat_buscar()){
                    case 1:
                        String buscarDIRECCIO = new Scanner(System.in).nextLine();
                        long count = films.stream().filter(line -> line.getDireccion().equals(buscarDIRECCIO)).count();
                        System.out.println( "result:" + count);
                        films.stream().filter(line -> line.getDireccion().equals(buscarDIRECCIO)).forEach(System.out::println);
                        break;
                    case 2:
                        int buscarAny = new Scanner(System.in).nextInt();
                        count = films.stream().filter(line -> line.getAny() == buscarAny).count();
                        System.out.println( "result:" + count);
                        films.stream().filter(line -> line.getAny() == buscarAny).forEach(System.out::println);
                        break;
                    default:
                }
                break;
            case 3:
                Comparator<Film> filmComparator = Comparator.comparing( Film::getAny );
                Film maxany = films.stream().max(filmComparator).get();
                System.out.println(maxany.getAny());
                break;
            case 4:
                filmComparator = Comparator.comparing( Film::getAny );
                Film minany = films.stream().min(filmComparator).get();
                System.out.println(minany.getAny());
                break;
            case 5:
                List<Film> distinctElements = films.stream()
                        .filter( distinctByKey(p -> p.getAny()))
                        .collect( Collectors.toList());
                distinctElements.forEach((k)->System.out.println("Any : " + k.getAny()));
                break;
            case 6:
                filmComparator = Comparator.comparing(Film::getAny );
                distinctElements = films.stream()
                        .sorted(filmComparator)
                        .filter( distinctByKey(Film::getAny))
                        .collect( Collectors.toList());
                distinctElements.forEach((k)->System.out.println("Any : " + k.getAny()));
                break;
            case 7:
                filmComparator = Comparator.comparing(Film::getAny );
                List<Film> distinctElements1 = films.stream()
                        .sorted(filmComparator)
                        .filter( distinctByKey(Film::getAny))
                        .collect( Collectors.toList());
                distinctElements1.forEach((k)->System.out.println("Any : " + k.getAny()));
                distinctElements1.stream().map(number -> number.getAny() + 1000).forEach(System.out::println);
                break;
            default:
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
