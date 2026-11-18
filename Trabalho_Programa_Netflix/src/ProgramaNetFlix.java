/*Gabriel Gomes dos Santos - 10748012, Kauê Trigolo Damaceno 10748319,  Henrique Orlando Rosa da Silva – 10753777  */

package src;
public class ProgramaNetFlix {
    private String id;
    private String title;
    private String type;
    private String description;
    private int releaseYear;
    private String ageCertification;
    private int runtime;
    private String genres;
    private String productionCountries;
    private double seasons;
    private String imdbId;
    private double imdbScore;
    private double imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    public ProgramaNetFlix(String id, String title, String type, String description, 
                           int releaseYear, String ageCertification, int runtime, 
                           String genres, String productionCountries, double seasons, 
                           String imdbId, double imdbScore, double imdbVotes, 
                           double tmdbPopularity, double tmdbScore) {
                            
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.releaseYear = releaseYear;
        this.ageCertification = ageCertification;
        this.runtime = runtime;
        this.genres = genres;
        this.productionCountries = productionCountries;
        this.seasons = seasons;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public String getAgeCertification() { return ageCertification; }
    public void setAgeCertification(String ageCertification) { this.ageCertification = ageCertification; }

    public int getRuntime() { return runtime; }
    public void setRuntime(int runtime) { this.runtime = runtime; }

    public String getGenres() { return genres; }
    public void setGenres(String genres) { this.genres = genres; }

    public String getProductionCountries() { return productionCountries; }
    public void setProductionCountries(String productionCountries) { this.productionCountries = productionCountries; }

    public double getSeasons() { return seasons; }
    public void setSeasons(double seasons) { this.seasons = seasons; }

    public String getImdbId() { return imdbId; }
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public double getImdbScore() { return imdbScore; }
    public void setImdbScore(double imdbScore) { this.imdbScore = imdbScore; }

    public double getImdbVotes() { return imdbVotes; }
    public void setImdbVotes(double imdbVotes) { this.imdbVotes = imdbVotes; }

    public double getTmdbPopularity() { return tmdbPopularity; }
    public void setTmdbPopularity(double tmdbPopularity) { this.tmdbPopularity = tmdbPopularity; }

    public double getTmdbScore() { return tmdbScore; }
    public void setTmdbScore(double tmdbScore) { this.tmdbScore = tmdbScore; }
}