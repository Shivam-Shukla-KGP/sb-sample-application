package in.jp.trial.sb.sample.application.model;

public class SampleApplicationModel {
    private Integer id;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SampleApplicationModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
