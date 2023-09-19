package iet.internal.new_odoo.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgramDTO {
    private Long id;
    private String name;
    private String programManager;
    private Long idCoo;
}