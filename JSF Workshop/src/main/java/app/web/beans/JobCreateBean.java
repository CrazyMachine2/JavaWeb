package app.web.beans;

import app.domain.entities.Sector;
import app.domain.models.binding.JobCreateBindingModel;
import app.domain.models.service.JobApplicationServiceModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean{

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private JobCreateBindingModel job;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.job = new JobCreateBindingModel();
    }

    public void create(){
        JobApplicationServiceModel model = this.modelMapper.map(this.job, JobApplicationServiceModel.class);
        Sector sector = null;
        try {
            sector = Sector.valueOf(this.job.getSector().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            this.redirect("/add-job");
        }
        model.setSector(sector);
        this.jobApplicationService.save(model);
        this.redirect("/home");
    }

    public JobCreateBindingModel getJob() {
        return job;
    }

    public void setJob(JobCreateBindingModel job) {
        this.job = job;
    }
}
