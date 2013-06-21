package com.metamx.druid.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 */
public class DbTablesConfig
{
  public static DbTablesConfig fromBase(String base)
  {
    return new DbTablesConfig(base, null, null, null, null, null, null);
  }

  @NotNull
  private final String base;

  private final String segmentsTable;

  private final String rulesTable;

  private final String configTable;

  private final String tasksTable;

  private final String taskLogTable;

  private final String taskLockTable;

  @JsonCreator
  public DbTablesConfig(
      @JsonProperty("base") String base,
      @JsonProperty("segments") String segmentsTable,
      @JsonProperty("rules") String rulesTable,
      @JsonProperty("config") String configTable,
      @JsonProperty("tasks") String tasksTable,
      @JsonProperty("taskLog") String taskLogTable,
      @JsonProperty("taskLock") String taskLockTable
  )
  {
    this.base = base;
    this.segmentsTable = makeTableName(segmentsTable, "segments");
    this.rulesTable = makeTableName(rulesTable, "rules");
    this.configTable = makeTableName(configTable, "config");
    this.tasksTable = makeTableName(tasksTable, "tasks");
    this.taskLogTable = makeTableName(taskLogTable, "task_log");
    this.taskLockTable = makeTableName(taskLockTable, "task_lock");
  }

  private String makeTableName(String explicitTableName, String defaultSuffix)
  {
    if (explicitTableName == null) {
      if (base == null) {
        return null;
      }
      return String.format("%s_%s", base, defaultSuffix);
    }

    return explicitTableName;
  }

  @JsonProperty
  public String getBase()
  {
    return base;
  }

  @JsonProperty("segments")
  public String getSegmentsTable()
  {
    return segmentsTable;
  }

  @JsonProperty("rules")
  public String getRulesTable()
  {
    return rulesTable;
  }

  @JsonProperty("config")
  public String getConfigTable()
  {
    return configTable;
  }

  @JsonProperty("tasks")
  public String getTasksTable()
  {
    return tasksTable;
  }

  @JsonProperty("taskLog")
  public String getTaskLogTable()
  {
    return taskLogTable;
  }

  @JsonProperty("taskLock")
  public String getTaskLockTable()
  {
    return taskLockTable;
  }
}