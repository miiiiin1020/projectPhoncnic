package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.GalleryImage;
import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class GalleryRepositoryTests {

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    GalleryImageRepository galleryImageRepository;

    @Transactional
    @Test
    @Commit
    public void insertDummise(){        
        IntStream.rangeClosed(1, 10).forEach(i->{

            boolean rand = ((int)(Math.random()*2))!=0;
            
            List<Integer> randmember = new ArrayList<>();

            while (randmember.size()!=10) {
                int inputrandomNumber = (int) (Math.random() * 10) + 1;
                for (int k = 0; k < 10; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
    
                }
            }

            Member member = 
                Member.builder().id(
                    memberRepository.findById("user"+i+"@icloud.com").get().getId()
                )
            .build();

            GalleryImage galleryImage = GalleryImage.builder()
                .imagename("imagename"+i)
                .imagepath(i+"imagepath.jpg")
                .imagetype(rand)
                .artistid(member)
            .build(); 
            
            Gallery gallery = Gallery.builder()
                .title(i+"title")
                .content(i+"content")
                .artistid(member)
            .build();

            int ra = (int)(Math.random()*5)+1;

            for(int j = 0; j<ra; j++){
                member = 
                    Member.builder().id("user"+randmember.get(j)+"@icloud.com")
                .build();

                Emoji emoji = Emoji.builder()
                    .gallery(gallery)
                    .emojitype((int)(Math.random()*4)+"")
                    .member(member)
                .build();
                emojiRepository.save(emoji);
            }
            
            gallery.setImage(galleryImage);
            galleryImage.setGallery(gallery);

            galleryImageRepository.save(galleryImage);
            galleryRepository.save(gallery);
        }
        );

    }

}
