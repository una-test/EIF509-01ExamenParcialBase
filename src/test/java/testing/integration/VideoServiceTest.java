package testing.integration;

import ac.cr.una.parcial01.model.Actor;
import ac.cr.una.parcial01.model.Movie;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceTest.class);

    @Before
    public void setUp() throws Exception {
        logger.info("-----> SETUP <-----");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("-----> DESTROY <-----");

    }

    @Test
    public void test01CascadeDeleteAll() throws Exception {
        videoService.deleteAllMovies();
    }

    @Test
    public void test02AddActor() throws Exception {

        Movie movie1 = new Movie();
        Movie movie2 = new Movie();

        movie1.setName("Furious 7");

        Actor actor1 = new Actor();
        actor1.setActorName("Vin Diesel");
        actor1.setMovie(movie1);

        Actor actor2= new Actor();
        actor2.setActorName("Paul Walker");
        actor2.setMovie(movie1);

        movie2.setName("The Dark Knight");

        Actor actor3 = new Actor();
        actor3.setActorName("Christian Bale");
        actor3.setMovie(movie2);

        Actor actor4= new Actor();
        actor4.setActorName("Heath Ledger");
        actor4.setMovie(movie2);

        actor1 = videoService.addActor(actor1);
        actor2 = videoService.addActor(actor2);
        actor3 = videoService.addActor(actor3);
        actor4 = videoService.addActor(actor4);

        assertEquals(actor1.getActorName(), "Vin Diesel");
        assertEquals(actor2.getActorName(), "Paul Walker");
        assertEquals(actor3.getActorName(), "Christian Bale");
        assertEquals(actor4.getActorName(), "Heath Ledger");
    }

    @Test
    public void test03GetMovieByActorName() throws Exception {
        Movie movie = null;

        movie = videoService.getMovieByActorName("Christian Bale");

        assertEquals(movie.getName(), "The Dark Knight");

    }

    @Test
    public void test04GetActorsByMovieName() throws Exception {
        Set<Actor> actors = null;

        actors = videoService.getActorsByMovieName("Furious 7");

        assertThat(actors.size(), equalTo(2));
    }
}
