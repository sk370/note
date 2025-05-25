CREATE TABLE `equipment_ledger` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `equipment_name` VARCHAR(100) NOT NULL COMMENT '设备名称',
  `equipment_code` VARCHAR(50) NOT NULL COMMENT '设备编号',
  `equipment_type` VARCHAR(50) NOT NULL COMMENT '设备类型',
  `supplier` VARCHAR(100) NOT NULL COMMENT '供应商',
  `manufacturer` VARCHAR(100) NOT NULL COMMENT '制造商',
  `is_critical` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否关键工序（0-否 1-是）',
  `is_special` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否特殊过程（0-否 1-是）',
  `maintenance_area` VARCHAR(100) NOT NULL COMMENT '检修区域',
  `location` VARCHAR(100) NOT NULL COMMENT '设备处所',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_equipment_code` (`equipment_code`),
  KEY `idx_equipment_name` (`equipment_name`),
  KEY `idx_equipment_type` (`equipment_type`),
  KEY `idx_maintenance_area` (`maintenance_area`),
  KEY `idx_location` (`location`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备台账表';


CREATE TABLE `maintenance_plan` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `equipment_name` VARCHAR(100) NOT NULL COMMENT '设备名称',
  `equipment_code` VARCHAR(50) NOT NULL COMMENT '设备编号',
  `equipment_type` VARCHAR(50) NOT NULL COMMENT '设备类型',
  `maintenance_type` VARCHAR(50) NOT NULL COMMENT '检修类型（例：日常保养/大修/项修）',
  `plan_date` DATE NOT NULL COMMENT '计划时间',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_equipment_code` (`equipment_code`),
  KEY `idx_maintenance_type` (`maintenance_type`),
  KEY `idx_plan_date` (`plan_date`),
  KEY `idx_update_time` (`update_time`),
  KEY `idx_equipment_plan` (`equipment_code`, `plan_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检修计划表';


CREATE TABLE `fault_daily_report` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `equipment_id` BIGINT UNSIGNED NOT NULL COMMENT '设备ID（关联设备台账）',
  `equipment_name` VARCHAR(100) NOT NULL COMMENT '设备名称',
  `equipment_code` VARCHAR(50) NOT NULL COMMENT '设备编号',
  `fault_date` DATE NOT NULL COMMENT '故障日期',
  `fault_description` TEXT NOT NULL COMMENT '故障描述',
  `fault_level` TINYINT COMMENT '故障等级（1-轻微 2-一般 3-严重）',
  `analysis_process` TEXT NOT NULL COMMENT '排查及分析情况',
  `disposal_method` TEXT NOT NULL COMMENT '处置情况',
  `preventive_measure` TEXT COMMENT '预防措施',
  `downtime_days` INT UNSIGNED NOT NULL COMMENT '故障天数（自然日）',
  `responsible_person` VARCHAR(50) NOT NULL COMMENT '负责人',
  `is_completed` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否完成（0-未完成 1-已完成）',
  `completion_proof` VARCHAR(200) COMMENT '完成佐证材料路径',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_fault_date` (`fault_date`),
  KEY `idx_responsible_person` (`responsible_person`),
  KEY `idx_is_completed` (`is_completed`),
  KEY `idx_fault_status` (`fault_date`, `is_completed`),
  KEY `idx_responsible_status` (`responsible_person`, `is_completed`),
  CONSTRAINT `fk_equipment_id` 
    FOREIGN KEY (`equipment_id`) 
    REFERENCES `equipment_ledger` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故障日报表';


CREATE TABLE `spare_parts` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `part_name` VARCHAR(100) NOT NULL COMMENT '备件名称',
  `specification` VARCHAR(200) NOT NULL COMMENT '规格型号',
  `brand` VARCHAR(50) NOT NULL COMMENT '品牌',
  `applicable_equipment` BIGINT UNSIGNED NOT NULL COMMENT '适用设备（关联设备表）',
  `technical_parameters` TEXT NOT NULL COMMENT '主要技术参数',
  `part_number` VARCHAR(50) NOT NULL COMMENT '品号（唯一业务编码）',
  `installation_location` VARCHAR(100) COMMENT '安装部位',
  `image_paths` JSON COMMENT '图片路径（支持多图）',
  `per_unit_quantity` INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '单机配置数量',
  `unit_stock_suggest` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '单机库存建议',
  `plant_stock_suggest` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '厂区库存建议',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_part_number` (`part_number`),
  KEY `idx_applicable_equipment` (`applicable_equipment`),
  KEY `idx_brand` (`brand`),
  KEY `idx_location` (`installation_location`),
  KEY `idx_stock_suggest` (`unit_stock_suggest`, `plant_stock_suggest`),
  CONSTRAINT `fk_applicable_equipment`
    FOREIGN KEY (`applicable_equipment`)
    REFERENCES `equipment_ledger` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备备品备件基础信息表';


CREATE TABLE `maintenance_schedule` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '排班ID',
  `worker_id` BIGINT UNSIGNED NOT NULL COMMENT '维修工ID（关联员工表）',
  `shift_type` TINYINT(1) NOT NULL COMMENT '班次类型（1-早班 2-中班 3-晚班）',
  `shift_date` DATE NOT NULL COMMENT '排班日期',
  `shift_start` TIME NOT NULL COMMENT '班次开始时间',
  `shift_end` TIME NOT NULL COMMENT '班次结束时间',
  `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '排班状态（0-待确认 1-已确认 2-已取消）',
  `note` VARCHAR(200) COMMENT '备注说明',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_schedule` (`worker_id`, `shift_date`),
  KEY `idx_shift_date` (`shift_date`),
  KEY `idx_shift_type` (`shift_type`),
  KEY `idx_worker_status` (`worker_id`, `status`),
  CONSTRAINT `fk_worker` 
    FOREIGN KEY (`worker_id`) 
    REFERENCES `workers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修工排班表';
