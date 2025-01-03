package org.example.backend.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.example.backend.entity.user.Child;
import org.example.backend.entity.user.ParentChildRelation;
import org.example.backend.service.user.ParentChildRelationService;
import org.example.backend.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parentChildRelation")
public class ParentChildRelationController {

  @Autowired private ParentChildRelationService parentChildRelationService;

  @Autowired private JsonParser jsonParser;

  // 根据userId获取所有关系表数据
  @PostMapping("/selectAllRelations")
  public ResponseEntity<String> selectAllRelations(HttpServletRequest request) {
    // 从请求中获取用户ID
    String userId = (String) request.getAttribute("userId");

    try {
      List<ParentChildRelation> relations = parentChildRelationService.getRelationsByUserId(userId);
      return ResponseEntity.ok(jsonParser.toJsonFromEntityList(relations));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }

  // 根据relationId删除关系
  @PostMapping("/deleteRelationById")
  public ResponseEntity<String> deleteRelationById(@RequestBody String relationIdJson) {
    try {
      int relationId = jsonParser.parseJsonInt(relationIdJson, "relationId");
      boolean isDeleted = parentChildRelationService.deleteRelationById(relationId);
      if (isDeleted) {
        return ResponseEntity.ok("删除成功");
      } else {
        return ResponseEntity.status(404).body("未找到相关数据");
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("删除失败");
    }
  }

  // 根据childId删除关系
  @PostMapping("/deleteRelationByChildId")
  public ResponseEntity<String> deleteRelationByChildId(@RequestBody String childIdJson) {
    try {
      String childId = jsonParser.parseJsonString(childIdJson, "childId");
      boolean isDeleted = parentChildRelationService.deleteRelationsByChildId(childId);
      if (isDeleted) {
        return ResponseEntity.ok("删除成功");
      } else {
        return ResponseEntity.status(404).body("未找到相关数据");
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("删除失败");
    }
  }

  // 根据userId获取所有孩子
  @PostMapping("/getChildrenByUserId")
  public ResponseEntity<String> getChildrenByUserId(@RequestBody String userIdJson) {
    try {
      String userId = jsonParser.parseJsonString(userIdJson, "userId");
      List<Child> children = parentChildRelationService.getChildrenByUserId(userId);
      return ResponseEntity.ok(jsonParser.toJsonFromEntityList(children));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }
}
