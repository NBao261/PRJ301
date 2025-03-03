package dto;

import java.util.Date;

public class StartupProjectDTO {

    private int projectId;
    private String projectName;
    private String description;
    private String status;
    private Date estimatedLaunch;
    private String username;

    public StartupProjectDTO() {
    }

    public StartupProjectDTO(int projectId, String projectName, String description,
            String status, Date estimatedLaunch, String username) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimatedLaunch = estimatedLaunch;
        this.username = username;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEstimatedLaunch() {
        return estimatedLaunch;
    }

    public void setEstimatedLaunch(Date estimatedLaunch) {
        this.estimatedLaunch = estimatedLaunch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
