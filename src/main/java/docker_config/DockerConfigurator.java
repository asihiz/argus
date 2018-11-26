import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerConfigurator {

    private static final DockerConfigurator INSTANCE = new DockerConfigurator();

    private DockerConfigurator(){
        //protect reflection
        if(INSTANCE != null){
            return;
        }
    }

    public static DockerConfigurator getInstance(){
        return INSTANCE;
    }

    public void initDOcker(){
        DefaultDockerClientConfig.Builder config
                = DefaultDockerClientConfig.createDefaultConfigBuilder();
        DockerClient dockerClient = DockerClientBuilder
                .getInstance(config)
                .build();
    }



}
