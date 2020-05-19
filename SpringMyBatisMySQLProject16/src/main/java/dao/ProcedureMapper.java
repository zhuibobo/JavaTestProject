package dao;

import java.util.List;
import java.util.Map;

public interface ProcedureMapper {
    void insertRecord(Map data);

    Map sp_insert_relationship_1to1_pc_out_param_1(Map data);

    List sp_insert_relationship_1to1_pc_out_param_2(Map data);
}
