package com.hqy.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import rx.functions.Action;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Studentervice {
    private String name;
    private String id;

    public static void main(String[] args) {

    }
}
