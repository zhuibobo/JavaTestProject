package Elasticsearch14;


import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/13
 * To change this template use File | Settings | File Templates.
 */
public class CreateIndex extends ElasticSearchConnect {

    @Test
    public void deleteIndexMess(){
        RestHighLevelClient client=getClient();
        DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("mess");
        try {
            AcknowledgedResponse acknowledgedResponse=client.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
            System.out.println(acknowledgedResponse.isAcknowledged());
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {
                System.out.println("不存在索引mess");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createIndexMess() {
        try (RestHighLevelClient client = getClient();) {

            // 1、创建 创建索引request 参数：索引名mess
            CreateIndexRequest request = new CreateIndexRequest("mess");

            // 2、设置索引的settings
            /*request.settings(Settings.builder().put("index.number_of_shards", 3) // 分片数
                    .put("index.number_of_replicas", 2) // 副本数
                    .put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
            );*/

            // 3、设置索引的mappings
            /*request.mapping("_doc",
                    "  {\n" +
                            "    \"_doc\": {\n" +
                            "      \"properties\": {\n" +
                            "        \"message\": {\n" +
                            "          \"type\": \"text\"\n" +
                            "        }\n" +
                            "      }\n" +
                            "    }\n" +
                            "  }",
                    XContentType.JSON);*/

            // 4、 设置索引的别名
            //request.alias(new Alias("mmm"));

            // 5、 发送请求
            // 5.1 同步方式发送请求
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

            // 6、处理响应
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse
                    .isShardsAcknowledged();
            System.out.println("acknowledged = " + acknowledged);
            System.out.println("shardsAcknowledged = " + shardsAcknowledged);

            // 5.1 异步方式发送请求
            /*ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(
                        CreateIndexResponse createIndexResponse) {
                    // 6、处理响应
                    boolean acknowledged = createIndexResponse.isAcknowledged();
                    boolean shardsAcknowledged = createIndexResponse
                            .isShardsAcknowledged();
                    System.out.println("acknowledged = " + acknowledged);
                    System.out.println(
                            "shardsAcknowledged = " + shardsAcknowledged);
                }

                @Override
                public void onFailure(Exception e) {
                    System.out.println("创建索引异常：" + e.getMessage());
                }
            };

            client.indices().createAsync(request, listener);
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
