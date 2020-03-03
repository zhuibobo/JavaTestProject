package Elasticsearch14.DocumentAPIs;

import Elasticsearch14.ConnAndSearch;
import Elasticsearch14.DIConfig002;
import Elasticsearch14.ElasticSearchConnect;
import Elasticsearch14.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/19
 * To change this template use File | Settings | File Templates.
 */
public class Index_TPE_INFLOW_INFO extends ElasticSearchConnect {

    @Test
    public void delete_TPE_INFLOW_INFO_Index(){
        RestHighLevelClient client=getClient();
        DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("tpe_inflow_info");
        try {
            AcknowledgedResponse acknowledgedResponse=client.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
            System.out.println(acknowledgedResponse.isAcknowledged());
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {
                System.out.println("不存在索引tpe_inflow_info");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create_TPE_INFLOW_INFO_Index(){
        try (RestHighLevelClient client = getClient()) {

            // 1、创建 创建索引request 参数：索引名mess
            CreateIndexRequest request = new CreateIndexRequest("tpe_inflow_info");

            Map<String, Object> id_FieldProp = new HashMap<>();
            id_FieldProp.put("type", "text");

            Map<String, Object> XM_FieldProp = new HashMap<>();
            XM_FieldProp.put("type", "keyword");

            Map<String, Object> XB_FieldProp = new HashMap<>();
            XB_FieldProp.put("type", "text");

            Map<String, Object> MZ_FieldProp = new HashMap<>();
            MZ_FieldProp.put("type", "text");

            Map<String, Object> IDCARD_FieldProp = new HashMap<>();
            IDCARD_FieldProp.put("type", "text");

            Map<String,Object> CSRQ_FieldProp=new HashMap<>();
            CSRQ_FieldProp.put("type", "date");

            Map<String, Object> AREA_XIAN_FieldProp = new HashMap<>();
            AREA_XIAN_FieldProp.put("type", "text");

            Map<String, Object> AREA_ZHENG_FieldProp = new HashMap<>();
            AREA_ZHENG_FieldProp.put("type", "text");

            Map<String, Object> AREA_CUN_FieldProp = new HashMap<>();
            AREA_CUN_FieldProp.put("type", "text");

            Map<String, Object> AREA_GRIDUNI_FieldProp = new HashMap<>();
            AREA_GRIDUNI_FieldProp.put("type", "text");

            Map<String, Object> HJS_FieldProp = new HashMap<>();
            HJS_FieldProp.put("type", "text");

            Map<String, Object> HJSHI_FieldProp = new HashMap<>();
            HJSHI_FieldProp.put("type", "text");

            Map<String, Object> HJX_FieldProp = new HashMap<>();
            HJX_FieldProp.put("type", "text");

            Map<String, Object> HJDZ_FieldProp = new HashMap<>();
            HJDZ_FieldProp.put("type", "text");
            HJDZ_FieldProp.put("analyzer", "ik_smart");

            Map<String, Object> HJLX_FieldProp = new HashMap<>();
            HJLX_FieldProp.put("type", "text");
            HJLX_FieldProp.put("analyzer", "ik_smart");

            Map<String, Object> fields = new HashMap<>();
            fields.put("ID", id_FieldProp);
            fields.put("XM", XM_FieldProp);
            fields.put("XB", XB_FieldProp);
            fields.put("MZ", MZ_FieldProp);
            fields.put("IDCARD", IDCARD_FieldProp);
            fields.put("AREA_XIAN", AREA_XIAN_FieldProp);
            fields.put("AREA_ZHENG", AREA_ZHENG_FieldProp);
            fields.put("AREA_CUN", AREA_CUN_FieldProp);
            fields.put("AREA_GRIDUNI", AREA_GRIDUNI_FieldProp);
            fields.put("HJS", HJS_FieldProp);
            fields.put("HJSHI", HJSHI_FieldProp);
            fields.put("HJX", HJX_FieldProp);
            fields.put("HJDZ", HJDZ_FieldProp);
            fields.put("HJLX", HJLX_FieldProp);
            fields.put("CSRQ",CSRQ_FieldProp);

            Map<String, Object> properties = new HashMap<>();
            properties.put("properties", fields);

            request.mapping(properties);

            // 5、 发送请求
            // 5.1 同步方式发送请求
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

            // 6、处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse
                    .isShardsAcknowledged();
            System.out.println("acknowledged = " + acknowledged);
            System.out.println("shardsAcknowledged = " + shardsAcknowledged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryList() throws JsonProcessingException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        int topNum=50;
        List<LinkedHashMap<String, Object>> mapList=connAndSearch.QueryTopListMap(topNum,dataSource);
        JsonUtility.prettyPrinter(mapList);
        Assert.assertEquals(topNum,mapList.size());
        System.out.println("人口总数："+topNum);
        //System.out.println(mapList);
    }

    @Test
    public void queryListAndIndexDocument() throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        int topNum=50;
        List<LinkedHashMap<String, Object>> mapList=connAndSearch.QueryTopListMap(topNum,dataSource);
        RestHighLevelClient client = getClient();
        for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : mapList) {
            JsonUtility.prettyPrinter(mapList);
            IndexRequest request = new IndexRequest("tpe_inflow_info");
            request.id(stringObjectLinkedHashMap.get("ID").toString());
            String jsonString = JsonUtility.toObjectString(stringObjectLinkedHashMap);
            request.source(jsonString, XContentType.JSON);

            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            String getId = indexResponse.getId();
            System.out.println("indexResponse.getId:" + getId);
        }

        Assert.assertEquals(topNum,mapList.size());
        System.out.println("人口总数："+topNum);
    }

    @Test
    public void queryListAndIndexDocumentBulkRequest() throws IOException{
        BulkRequest request = new BulkRequest("tpe_inflow_info");

        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        int topNum=100000;
        List<LinkedHashMap<String, Object>> mapList=connAndSearch.QueryTopListMap(topNum,dataSource);
        RestHighLevelClient client = getClient();
        for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : mapList) {
            //JsonUtility.prettyPrinter(mapList);
            String jsonString = JsonUtility.toObjectString(stringObjectLinkedHashMap);
            String id=stringObjectLinkedHashMap.get("ID").toString();
            request.add(
                    new IndexRequest("tpe_inflow_info").id(id).source(jsonString, XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        Assert.assertEquals(topNum,mapList.size());
        System.out.println("人口总数："+topNum);
    }
}
