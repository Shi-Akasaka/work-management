package com.example.workmanagement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {

    private final PlaceRepository placeRepository;

    // コンストラクタ
    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    // =========================
    // 作業一覧取得
    // GET /place
    // =========================
    @GetMapping("/place")
    public ResponseEntity<List<Place>> getPlaceList() {

        List<Place> placeList = placeRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(placeList);
    }


    // =========================
    // 同じ園地の作業一覧取得
    // GET /place/search?place=園地名
    // =========================
    @GetMapping("/place/search")
    public ResponseEntity<List<Place>> searchPlace(
            @RequestParam String place) {

        List<Place> result = placeRepository.findByPlace(place);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


    // =========================
    // 作業詳細取得
    // GET /place/{id}
    // =========================
    @GetMapping("/place/{id}")
    public ResponseEntity<?> getPlace(
            @PathVariable int id) {

        return placeRepository.findById(id)
                .map(place -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(place))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    // =========================
    // 作業登録
    // POST /place
    // =========================
    @PostMapping("/place")
    public ResponseEntity<?> addPlace(
            @RequestBody Place place) {

        // 入力チェック
        if (place.getPlace() == null ||
            place.getPlace().isEmpty() ||

            place.getDate() == null ||
            place.getDate().isEmpty() ||

            place.getName() == null ||
            place.getName().isEmpty() ||

            place.getWorker() == null ||
            place.getWorker().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("入力項目が不足しています");
        }


        // データベースに登録
        Place savedPlace = placeRepository.save(place);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPlace);
    }


    // =========================
    // 作業更新
    // PUT /place/{id}
    // =========================
    @PutMapping("/place/{id}")
    public ResponseEntity<?> updatePlace(
            @PathVariable int id,
            @RequestBody Place newPlace) {

        // 入力チェック
        if (newPlace.getPlace() == null ||
            newPlace.getPlace().isEmpty() ||

            newPlace.getDate() == null ||
            newPlace.getDate().isEmpty() ||

            newPlace.getName() == null ||
            newPlace.getName().isEmpty() ||

            newPlace.getWorker() == null ||
            newPlace.getWorker().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("入力項目が不足しています");
        }


        // 更新対象を検索
        if (!placeRepository.existsById(id)) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("対象データがありません");
        }


        // URLのIDを設定
        newPlace.setPlaceId(id);


        // データベースを更新
        Place updatedPlace = placeRepository.save(newPlace);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPlace);
    }


    // =========================
    // 作業削除
    // DELETE /place/{id}
    // =========================
    @DeleteMapping("/place/{id}")
    public ResponseEntity<?> deletePlace(
            @PathVariable int id) {

        // 削除対象が存在するか確認
        if (!placeRepository.existsById(id)) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("対象データがありません");
        }


        // データベースから削除
        placeRepository.deleteById(id);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body("削除しました");
    }
}