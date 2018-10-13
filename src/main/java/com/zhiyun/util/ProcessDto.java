package com.zhiyun.util;

import java.util.List;

/**
 * @Auther: sunyuntao
 * @Date: 2018/6/25 15:26
 * @Description:
 */
public class ProcessDto {

    private String message;

    private Integer status;

    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Task {

        private String taskName;

        private String assignee;

        private String taskId;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
    }

    public static class Data {

        private String processInstanceId;

        private String flowState;

        private List<Task> tasks;

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getFlowState() {
            return flowState;
        }

        public void setFlowState(String flowState) {
            this.flowState = flowState;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }
    }
}
