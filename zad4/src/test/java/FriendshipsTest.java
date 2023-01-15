
import org.example.FriendsCollection;
import org.example.FriendshipsMongo;
import org.example.Person;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FriendshipsTest {
    @InjectMocks FriendshipsMongo friendships_mongo;
    @Mock FriendsCollection friends_collection;

    @Test
    public void workingMock(){
        Person bob = new Person("Bob");
        when(friends_collection.findByName("Bob")).thenReturn(bob);
        assertEquals(bob, friends_collection.findByName("Bob"));
    }

    @Test
    public void listOfFriends(){
        List<String> friends_list = List.of("Michal", "Jan");
        Person bob = mock(Person.class);
        when(friends_collection.findByName("bob")).thenReturn(bob);
        when(bob.getFriends()).thenReturn(friends_list);
        assertThat(friendships_mongo.getFriendsList("bob")).hasSize(2).containsOnly("Michal", "Jan");
    }

    @Test
    public void withoutMockFriends(){
        assertEquals( 0, friendships_mongo.getFriendsList("Someone").size());
    }

    @Test
    public void testMakeFriends(){
        Person bob = new Person("Bob");
        Person tim = new Person("Tim");
        when(friends_collection.findByName("Bob")).thenReturn(bob);
        when(friends_collection.findByName("Tim")).thenReturn(tim);
        friendships_mongo.makeFriends("Bob", "Tim");
        assertEquals(bob, friends_collection.findByName("Bob"));
    }

    @Test
    public void testAreFriends(){
        Person bob = new Person("Bob");
        when(friends_collection.findByName("Bob")).thenReturn(bob);
        friendships_mongo.areFriends("Bob", "Tim");
        assertEquals(bob, friends_collection.findByName("Bob"));
    }


}
