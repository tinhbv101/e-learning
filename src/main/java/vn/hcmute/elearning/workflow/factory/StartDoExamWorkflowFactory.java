package vn.hcmute.elearning.workflow.factory;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.config.CadenceWorkflowConfigManager;
import vn.hcmute.elearning.enums.CadenceWorkflowTask;
import vn.hcmute.elearning.workflow.activity.interfaces.IStartDoExamWorkflowActivity;
import vn.hcmute.elearning.workflow.workflow.interfaces.IStartDoExamWorkflow;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDoExamWorkflowFactory {
    private final CadenceWorkflowConfigManager configManager;
    private final WorkflowClient workflowClient;

    public Map<String, ActivityOptions> getActivitiesOptionsMap() {
        Map<String, ActivityOptions> map = new HashMap<>();
        map.put(IStartDoExamWorkflowActivity.class.getSimpleName(), configManager.getActivityOptions(
            CadenceWorkflowTask.START_DO_EXAM_TASK.getName()
            , IStartDoExamWorkflowActivity.class.getSimpleName()
        ));
        return map;
    }

    public IStartDoExamWorkflow newWorkflow() {
        WorkflowOptions options = configManager.getWorkflowOptions(CadenceWorkflowTask.START_DO_EXAM_TASK.getName(), CadenceWorkflowTask.START_DO_EXAM_TASK.getId());
        log.debug(options.toString());
        return workflowClient.newWorkflowStub(IStartDoExamWorkflow.class, options);
    }
}
