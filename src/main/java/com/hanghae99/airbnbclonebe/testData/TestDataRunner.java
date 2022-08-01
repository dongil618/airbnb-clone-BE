package com.hanghae99.airbnbclonebe.testData;

import com.hanghae99.airbnbclonebe.model.*;
import com.hanghae99.airbnbclonebe.repository.*;
import com.hanghae99.airbnbclonebe.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TestDataRunner implements ApplicationRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    WishRepository wishRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User testUser1 = new User("dongil618", passwordEncoder.encode("1234"),"USER");
        User testUser2 = new User("rlaehddlf618", passwordEncoder.encode("1234"), "USER");
        User testUser3 = new User("ehddlf618", passwordEncoder.encode("1234"), "USER");
        userRepository.save(testUser1);
        userRepository.save(testUser2);
        userRepository.save(testUser3);


        createRoom(testUser1, testUser2, testUser3);
        createWish(testUser1, testUser2, testUser3);
        createImage();
        createOption();

    }

    public void  createRoom(User testUser1, User testUser2, User testUser3){

        // Room 20개 생성
        for(int i = 1; i <= 20; i++){
            if(i < 7){
                saveRoom(i, testUser1);
            } else if (i < 14) {
                saveRoom(i, testUser2);
            } else {
                saveRoom(i, testUser3);
            }
        }
    }

    public void createWish(User testUser1, User testUser2, User testUser3){

        // Wish 10개 생성
        for(int i = 1; i <= 10; i++){
            if(i < 3){
                saveWish(i, testUser1);
            } else if (i < 7) {
                saveWish(i, testUser2);
            } else {
                saveWish(i, testUser3);
            }
        }
    }

    public void  createImage(){

        // Image 40개 생성(각 숙소당 2장의 사진)
        for(int i = 1; i <= 40; i++){
            saveImage(i);
        }
    }

    public void createOption(){
        // 각각의 Room마다 1개의 옵션 랜덤으로 설정
        for(int i = 1; i <= 20; i++){
            saveOption(i);
        }
    }

    public void saveOption(int i){
        if(i % 2 == 0){
            Long roomId = (long) i/2;
            saveOptionDuplicate(roomId, i);
        } else {
            Long roomId = (long) i/2 + 1;
            saveOptionDuplicate(roomId, i);
        }
    }

    public void saveOptionDuplicate(Long roomId, int i){
        Random random = new Random();

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소정보가 없습니다."));

        OptionEnum[] optionList = {OptionEnum.PARKING, OptionEnum.KITCHEN, OptionEnum.AIRCON, OptionEnum.WIFI, OptionEnum.WASHER, OptionEnum.TV};
        OptionEnum optionName = optionList[random.nextInt(6)];

        Option option = new Option((long)i, room, optionName);
        optionRepository.save(option);
    }

    public void saveImage(int i){
        if(i % 2 == 0){
            Long roomId = (long) i/2;
            saveImageDuplicate(roomId, i);
        } else {
            Long roomId = (long) i/2 + 1;
            saveImageDuplicate(roomId, i);
        }
    }


    public void saveImageDuplicate(Long roomId, int i){
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소정보가 없습니다."));

        String imgUrl = getRandomString(20);

        Image image = new Image((long)i, room, imgUrl);

        imageRepository.save(image);
    }

    public void saveWish(int i, User testUser){
        Random random = new Random();

        Long roomId = (long) (random.nextInt(20) + 1);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 숙소정보가 없습니다."));

        Wish wish = new Wish((long)i, testUser, room);

        wishRepository.save(wish);
    }

    public void saveRoom(int i, User testUser){
        List<Image> imageList= new ArrayList<>();
        List<Wish> wishList = new ArrayList<>();
        List<Option> optionList = new ArrayList<>();

        Random random = new Random();

        String title = getRandomString(5);

        String location = getRandomString(10);

        String information = getRandomString(20);

        String[] categoryList = {"apartment", "offistel", "house"};
        String category = categoryList[random.nextInt(3)];

        int maxGuest = random.nextInt(4)+1;

        int price = (random.nextInt(10)+1) * 10000;

        Room room = new Room((long)i, title, testUser, maxGuest, price, location,information,category, optionList, imageList, wishList);

        roomRepository.save(room);
    }

    public static String getRandomString(int targetStringLength){
        Random random = new Random();

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}