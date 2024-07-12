import java.util.*;

class  User {
private String name;
private String email;
private String password;

public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        }
    public List<Movie> searchMovie(String movieName, List<Theater> theaters) {
        List<Movie> result = new ArrayList<>();
        for (Theater theater : theaters) {
            for (Movie movie : theater.getMovies()) {
                if (movie.getTitle().equalsIgnoreCase(movieName)) {
                    result.add(movie);
                }
            }
        }
        return result;
    }

    public List<Movie> searchTheater(String theaterName, List<Theater> theaters) {
        for (Theater theater : theaters) {
            if (theater.getName().equalsIgnoreCase(theaterName)) {
                return theater.getMovies();
            }
        }
        return new ArrayList<>();
    }
}

class Movie {
    private String title;
    private String genre;
    private int duration;
    private double rating;
    private List<Showtime> showtimes;

    public Movie(String title, String genre, int duration, double rating) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.showtimes = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public String getDetails() {
        return "Title: " + title + ", Genre: " + genre + ", Duration: " + duration + " minutes, Rating: " + rating;
    }
}

class Theater {
    private String name;
    private String location;
    private List<Movie> movies;

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
        this.movies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

class Showtime {
    private String time;
    private int availableSeats;

    public Showtime(String time, int availableSeats) {
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public String getTime() {
        return time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeats(int seats) {
        if (seats <= availableSeats) {
            availableSeats -= seats;
            return true;
        } else {
            return false;
        }
    }
}

class Booking {
    private User user;
    private Movie movie;
    private Theater theater;
    private Showtime showtime;
    private int seats;

    public Booking(User user, Movie movie, Theater theater, Showtime showtime, int seats) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.showtime = showtime;
        this.seats = seats;
    }

    public boolean confirmBooking() {
        return showtime.bookSeats(seats);
    }

    public String getBookingDetails() {
        return "User: " + user + ", Movie: " + movie.getTitle() + ", Theater: " + theater.getName() + ", Showtime: " + showtime.getTime() + ", Seats: " + seats;
    }
}

public class BookMyShow {
    public static void main(String[] args) {
        // Sample data
        User user = new User("John Doe", "john@example.com", "password123");

        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);
        Movie movie2 = new Movie("Titanic", "Romance", 195, 7.8);

        Showtime showtime1 = new Showtime("10:00 AM", 50);
        Showtime showtime2 = new Showtime("02:00 PM", 50);
        Showtime showtime3 = new Showtime("06:00 PM", 50);

        movie1.addShowtime(showtime1);
        movie1.addShowtime(showtime2);
        movie2.addShowtime(showtime3);

        Theater theater1 = new Theater("PVR Cinemas", "Downtown");
        Theater theater2 = new Theater("INOX", "Uptown");

        theater1.addMovie(movie1);
        theater1.addMovie(movie2);
        theater2.addMovie(movie1);

        List<Theater> theaters = Arrays.asList(theater1, theater2);

        // User searches for a movie
        List<Movie> foundMovies = user.searchMovie("Inception", theaters);
        for (Movie movie : foundMovies) {
            System.out.println(movie.getDetails());
        }

        // User searches for a theater
        List<Movie> theaterMovies = user.searchTheater("PVR Cinemas", theaters);
        for (Movie movie : theaterMovies) {
            System.out.println(movie.getDetails());
        }

        // Booking a ticket
        Booking booking = new Booking(user, movie1, theater1, showtime1, 2);
        if (booking.confirmBooking()) {
            System.out.println("Booking confirmed! Details: " + booking.getBookingDetails());
        } else {
            System.out.println("Booking failed. Not enough seats available.");
        }
    }
}
