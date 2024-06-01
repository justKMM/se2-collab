package EntityTest;

import hbrs.se2.collhbrs.model.entity.Rating;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class RatingTest {

    Rating rating;
    Rating rating1;

    @Mock
    Business business;

    @Mock
    Student student;

    @BeforeEach
    public void setUp() {
        rating = new Rating();
        rating.setRatingID(1L);
        rating.setBusiness(business);
        rating.setStudent(student);
        rating.setRatingScoreBusiness(5);
        rating.setRatingDescriptionBusiness("Top");
        rating.setRatingScoreStudent(5);
        rating.setRatingDescriptionStudent("Auch Top");

        rating1 = new Rating();
        rating1.setRatingID(1L);
        rating1.setBusiness(business);
        rating1.setStudent(student);
        rating1.setRatingScoreBusiness(5);
        rating1.setRatingDescriptionBusiness("Top1");
        rating1.setRatingScoreStudent(5);
        rating1.setRatingDescriptionStudent("Auch Top1");

    }
    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, rating.getRatingID());
        assertEquals(business, rating.getBusiness());
        assertEquals(student, rating.getStudent());
        assertEquals(5, rating.getRatingScoreBusiness());
        assertEquals("Top", rating.getRatingDescriptionBusiness());
        assertEquals(5, rating.getRatingScoreStudent());
        assertEquals("Auch Top", rating.getRatingDescriptionStudent());

        rating.setRatingDescriptionBusiness("Bad");
        assertEquals("Bad", rating.getRatingDescriptionBusiness());
        assertNotEquals("Top", rating.getRatingDescriptionBusiness());
    }
    @Test
    public void testEquals() {
        assertTrue(rating.equals(rating));
        assertTrue(rating1.equals(rating1));
        assertFalse(rating.equals(rating1));
    }
    @Test
    public void testHashCode() {
        assertNotEquals(rating.hashCode(),rating1.hashCode());
        assertEquals(rating.hashCode(),rating.hashCode());
    }
}
