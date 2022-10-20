package com.amazon.ata.music.playlist.service.dynamodb;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.exceptions.AlbumTrackNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class AlbumTrackDaoTest {

    @InjectMocks
    private AlbumTrackDao albumTrackDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    void init() {
        openMocks(this);
        albumTrackDao = new AlbumTrackDao(dynamoDBMapper);
    }

    @Test
    void getAlbumTrack_goodInfo_returnsAlbumTrack() {
        //given "good info"
        String validAsin = "testAsin";
        int validTrackNumber = 1;
        AlbumTrack testTrack = new AlbumTrack();
        testTrack.setTrackNumber(1);
        testTrack.setAsin(validAsin);

        //when we call getAlbumTrack
        when(dynamoDBMapper.load(AlbumTrack.class, validAsin, validTrackNumber)).thenReturn(testTrack);
        AlbumTrack result = albumTrackDao.getAlbumTrack(validAsin, 1);
        //check that it returned the right object
        assertEquals(testTrack, result);
    }

    @Test
    void getAlbumTrack_invalidInfo_throwsAlbumTrackNotFoundException() {
        //given
        String invalidAsin = "asdfasdfasdfasdfas";
        int testTrackNumber = 0;
        AlbumTrack invalid = new AlbumTrack();
        invalid.setAsin(invalidAsin);
        invalid.setTrackNumber(testTrackNumber);

        //when
        when(dynamoDBMapper.load(AlbumTrack.class, invalidAsin, testTrackNumber)).thenReturn(null);

        assertThrows(AlbumTrackNotFoundException.class, () -> albumTrackDao.getAlbumTrack(invalidAsin, testTrackNumber));

    }
}
